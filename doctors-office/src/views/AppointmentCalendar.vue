<template>
  <div style="margin-top: 60px"></div>
  <div class="container" style="margin-bottom: 30px">
    <div class="row">
      <div class="col">
        <h1>Calendar</h1>
        <FullCalendar :options="calendarOptions" />
        <br />
      </div>
    </div>

    <div class="row d-flex justify-content-center">
      <button
        v-if="!createNewAppointment.formVisible && !showSelectedReservation"
        class="btn btn-primary mx-2 col-4"
        v-on:click="createNewAppointment.formVisible = true"
      >
        Dodaj pregled
      </button>
    </div>

    <div
      v-if="createNewAppointment.formVisible"
      class="row d-flex justify-content-center"
      style="margin-top: 30px"
    >
      <div class="col-10">
        <div class="card">
          <div class="card-body">
            <h4 style="display: inline" class="card-title">
              Kreirajte novi pregled
            </h4>
            <span
              style="display: inline; float: right"
              class="form-check form-switch"
            >
              <input
                v-model="oldPatient"
                class="form-check-input"
                type="checkbox"
              />
            </span>
            <br />
            <div
              style="float: right; font-style: italic; zoom: 0.8"
              v-if="oldPatient"
            >
              Postojeći pacijent
            </div>
            <div style="float: right; font-style: italic; zoom: 0.8" v-else>
              Novi pacijent
            </div>
            <br />
            <span style="margin-top: 1.33em" class="row">
              <div class="col-5">
                <Datepicker
                  v-on:click="showcreateNewAppointmentForm()"
                  v-model="createNewAppointment.date"
                  range
                ></Datepicker>
                <select
                  class="form-select"
                  v-model="createNewAppointment.type"
                  style="margin-top: 30px"
                >
                  <option
                    v-for="option in services"
                    :key="option.id"
                    :value="option"
                  >
                    {{ option.name }}
                  </option>
                </select>
              </div>
              <div style="padding-left: 5%" class="col-6">
                <PatientList
                  :existingPatient="oldPatient"
                  @chosenPerson="changeChosenPerson"
                />
                <div v-if="!oldPatient">
  <form class="row d-flex justify-content-between">
  <div class="col-6">
   <input type="text" class="form-control" v-model="newPatient.firstName" placeholder="Ime">
  </div>
  <div class="col-6">
    <input type="text" class="form-control" v-model="newPatient.lastName" placeholder="Prezime">
  </div>
</form>
<form style="margin-top: 1.8em;" class="row d-flex justify-content-between">
  <div class="col-6">
   <input type="text" class="form-control" v-model="newPatient.phoneNumber" placeholder="Broj telefona">
  </div>
  <div class="col-6">
    <input type="text" class="form-control" v-model="newPatient.personalID" placeholder="JMBG (opciono)">
  </div>
</form>
</div>
              </div>
            </span>
            <br />
            <p class="card-text text-danger">{{ createNewAppointment.msg }}</p>
            <button
              class="btn btn-danger m-1"
              v-on:click="createNewAppointment.formVisible = false"
            >
              Odustani
            </button>
            <button
              :disabled="!createNewAppointment.valid"
              class="btn btn-success m-1"
              v-on:click="addNewAppointment()"
            >
              Kreiraj
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

import Datepicker from "vue3-date-time-picker";
import "vue3-date-time-picker/dist/main.css";

// FULL CALNEDAR
import "@fullcalendar/core/vdom"; // solves problem with Vite
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import listPlugin from "@fullcalendar/list";
import interactionPlugin, { Draggable } from "@fullcalendar/interaction";
import PatientList from "@/components/PatientList.vue";

export default {
  name: "AppointmentCalendar",
  components: {
    FullCalendar,
    Datepicker,
    PatientList,
  },
  data: function () {
    return {
      terms: [],

      selectedReservation: {},
      showSelectedReservation: false,

      createNewAppointment: {
        date: [],
        formVisible: false,
        type: {},
        msg: "",
        valid: true,
      },

      calendarOptions: {
        plugins: [dayGridPlugin, listPlugin, timeGridPlugin, interactionPlugin],
        initialView: "timeGridWeek",
        selectable: true,
        headerToolbar: {
          left: "prev,next today",
          center: "title",
          right: "dayGridMonth,timeGridWeek,timeGridDay",
        },
        events: [],
      },
      oldPatient: true,
      services: [],
      chosenPatientId: "",
      newPatient:{
        firstName: "",
        lastName:"",
        phoneNumber: "",
        personalID: ""
      },
      appointments:[]
    };
  },
  mounted: function () {
    this.calendarOptions.select = this.selectInCalendar;
    this.calendarOptions.eventClick = this.eventClickCalendar;
     axios.defaults.headers.common.Authorization =
        "Bearer " + window.sessionStorage.getItem("jwt");
    axios
      .get("http://localhost:8180/api/v1/services/doctor")
      .then((response) => {
        this.services = response.data;
      });
      axios
      .get("http://localhost:8180/api/v1/doctor-appointments")
      .then((response) => {
        this.appointments = response.data;
            this.calendarOptions.events = [];
            for (let a of this.appointments) {
                console.log(a)
                a.display = 'auto'
                a.textColor = "white"
                a.backgroundColor = "#0DE6FD"
                a.borderColor = "#0DE6FD"
                a.description = "opis"
                a.editable = false
                a.overlap = false
                a.title="aa"
                this.calendarOptions.events.push(a);
            }
      });
      
  },
  methods: {
    showcreateNewAppointmentForm: function () {
      this.createNewAppointment.msg = "";
      this.createNewAppointment.formVisible = true;
      this.createNewAppointment.valid = true;
    },
    selectInCalendar: function (selectedDate) {
      if (this.showSelectedReservation) return;
      this.createNewAppointment.msg = "";
      this.createNewAppointment.valid = true;
      this.createNewAppointment.date[0] = new Date(selectedDate.start);
      this.createNewAppointment.date[1] = new Date(
        new Date(selectedDate.end) - 1000
      );
      this.createNewAppointment.formVisible = true;
      if (this.overlap()) {
        this.createNewAppointment.msg =
          "Error, new term have overlap with other term";
        this.createNewAppointment.valid = false;
      }
    },
    addNewAppointment: async function () {
      if (!this.oldPatient) {
        axios.defaults.headers.common.Authorization =
        "Bearer " + window.sessionStorage.getItem("jwt");
        const response = await axios.post(
          "http://localhost:8180/api/v1/patients",
          this.newPatient
        );
        
        if (!response.data) {
          return;
        }

        this.chosenPatientId = response.data.id;
      }

      const pickedServices = [];
      pickedServices.push(this.createNewAppointment.type);
      axios.defaults.headers.common.Authorization =
        "Bearer " + window.sessionStorage.getItem("jwt");
      axios
        .post("http://localhost:8180/api/v1/appointments", {
          patientID: this.chosenPatientId,
          startTime: new Date(
            Date.UTC(
              this.createNewAppointment.date[0].getFullYear(),
              this.createNewAppointment.date[0].getMonth(),
              this.createNewAppointment.date[0].getDate(),
              this.createNewAppointment.date[0].getHours(),
              this.createNewAppointment.date[0].getMinutes(),
              this.createNewAppointment.date[0].getSeconds()
            )
          ),
          endTime: new Date(
            Date.UTC(
              this.createNewAppointment.date[1].getFullYear(),
              this.createNewAppointment.date[1].getMonth(),
              this.createNewAppointment.date[1].getDate(),
              this.createNewAppointment.date[1].getHours(),
              this.createNewAppointment.date[1].getMinutes(),
              this.createNewAppointment.date[1].getSeconds()
            )
          ),
          services: pickedServices,
          medicalWorkerID: 1,
        })
        .then((response) => {
          console.log(response.data);
        });
    },
    overlap: function () {
      for (const event of this.calendarOptions.events) {
        console.log(event.end)
        console.log(new Date(event.end))
        console.log(this.createNewAppointment.date[0])
        console.log(this.createNewAppointment.date[1])
        console.log(new Date(event.start))
        if (
          !(
            new Date(event.end) < this.createNewAppointment.date[0] ||
            this.createNewAppointment.date[1] < new Date(event.start)
          )
        ) {
          console.log("OVERLAP");
          return true;
        }
      }
      return false;
    },
    eventClickCalendar: function (info) {
      if (this.createNewAppointment.formVisible) return;

      let e = info.event._def.extendedProps;
      this.loadDataSelectedReservation(e);
    },
    loadDataSelectedReservation: function (event) {
      this.selectedReservation = null;
      this.showSelectedReservation = false;
    },
    closeSelectedReservation: function () {
      this.selectedReservation = null;
      this.showSelectedReservation = false;
    },
    changeChosenPerson(value) {
      this.chosenPatientId = value;
    },
  },
};
</script>


<style scoped>
</style>
