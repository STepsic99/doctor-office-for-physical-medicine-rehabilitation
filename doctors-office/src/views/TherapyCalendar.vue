<template>
  <div style="margin-top: 60px"></div>
  <div class="container" style="margin-bottom: 30px">
    <div v-if="!showAppointmentReport" class="row">
      <div class="col">
        <h1>Calendar</h1>
        <FullCalendar :options="calendarOptions" />
        <br />
      </div>

      <div class="row d-flex justify-content-center">
        <button
          v-if="!createNewAppointment.formVisible && !showSelectedReservation"
          class="btn btn-primary mx-2 col-4"
          v-on:click="createNewAppointment.formVisible = true"
        >
          Zakažite terapije
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
                Zakažite terapije
              </h4>
              <br />
              <div
                style="
                  margin-top: 1.33em;
                  width: 98%;
                  margin-right: 0;
                  margin-bottom: 5em;
                "
              >
                
              </div>
              <div>
                <table width="100%" style="border-collapse:separate; border-spacing:1em 1em">
                    <tr>
                        <td colspan="3"><PatientList
                  :existingPatient="oldPatient"
                  @chosenPerson="changeChosenPerson"
                /></td>
                    </tr>
                  <tr v-for="app in newAppointments" :key="app.id">
                    <td>
                      <Datepicker
                        v-on:click="showcreateNewAppointmentForm()"
                        v-model="app.date"
                        range
                      ></Datepicker>
                    </td>
                    <td>
                      <select
                        class="form-select"
                        v-model="app.type"
                        style="display: inline-block; float: right"
                      >
                        <option
                          v-for="option in services"
                          :key="option.id"
                          :value="option"
                        >
                          {{ option.name }}
                        </option>
                      </select>
                    </td>
                    <td style="text-align:right">
                        <i
            class="fa fa-times"
            aria-hidden="true"
            style="color: red; zoom: 2; cursor: pointer"
            v-on:click="subAppointment(app)"
                            ></i>
                        </td>
                  </tr>
                </table>
              </div>
              <br />
              <p class="card-text text-danger">
                {{ createNewAppointment.msg }}
              </p>
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
  </div>
</template>

<script>
import axios from "axios";

import Datepicker from "vue3-date-time-picker";
import "vue3-date-time-picker/dist/main.css";

import "@fullcalendar/core/vdom";
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import listPlugin from "@fullcalendar/list";
import interactionPlugin, { Draggable } from "@fullcalendar/interaction";
import PatientList from "@/components/PatientList.vue";
import Report from "@/components/Report.vue";

export default {
  name: "TherapyCalendar",
  components: {
    FullCalendar,
    Datepicker,
    PatientList,
    Report,
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

      newAppointments:[],

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
        hiddenDays: [0],
        businessHours: [
          {
            daysOfWeek: [6],
            startTime: "08:00",
            endTime: "13:00",
          },
          {
            daysOfWeek: [1, 2, 3, 4, 5],
            startTime: "08:00",
            endTime: "16:00",
          },
        ],
        slotMinTime: "08:00:00",
        slotMaxTime: "16:00:00",
        contentHeight: "auto",
      },
      oldPatient: true,
      services: [],
      chosenPatientId: "",
      newPatient: {
        firstName: "",
        lastName: "",
        phoneNumber: "",
        personalID: "",
      },
      appointments: [],
      showAppointmentReport: false,
      selectedEvent: {},
      selectedAppointment: {},
      cnt: 0
    };
  },
  mounted: function () {
    this.calendarOptions.select = this.selectInCalendar;
    this.calendarOptions.eventClick = this.eventClickCalendar;
    axios.defaults.headers.common.Authorization =
      "Bearer " + window.sessionStorage.getItem("jwt");
    axios
      .get("http://localhost:8180/api/v1/services/physiotherapist")
      .then((response) => {
        this.services = response.data;
      });
    axios
      .get("http://localhost:8180/api/v1/physiotherapist-appointments")
      .then((response) => {
        this.appointments = response.data;
        this.calendarOptions.events = [];
        for (let a of this.appointments) {
          console.log(a);
          a.display = "auto";
          a.textColor = "white";
          a.backgroundColor = "#0DE6FD";
          a.borderColor = "#0DE6FD";
          a.description = "opis";
          a.editable = false;
          a.overlap = false;
          a.title = a.services;
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
        console.log(this.newAppointments)
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
      this.cnt++;
      this.newAppointments.push({id:this.cnt, date:[new Date(selectedDate.start),new Date(new Date(selectedDate.end) - 1000)]})
    },
    subAppointment(a){
      for (var i = 0; i < this.newAppointments.length; i++) {
        if (this.newAppointments[i].id == a.id) {
          this.newAppointments.splice(i, 1);
          break;
        }
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
          medicalWorkerID: 1,
          appointments: [
            {
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
            },
          ],
        })
        .then((response) => {
          console.log(response.data);
          window.location.reload();
        });
    },
    overlap: function () {
      for (const event of this.calendarOptions.events) {
        console.log(event.end);
        console.log(new Date(event.end));
        console.log(this.createNewAppointment.date[0]);
        console.log(this.createNewAppointment.date[1]);
        console.log(new Date(event.start));
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

      let e = info.event;
      this.loadDataSelectedReservation(e);
    },
    loadDataSelectedReservation: function (event) {
      this.selectedEvent = event;
      axios.defaults.headers.common.Authorization =
        "Bearer " + window.sessionStorage.getItem("jwt");
      axios
        .get(
          "http://localhost:8180/api/v1/doctor-appointments/" +
            event.extendedProps.appID
        )
        .then((response) => {
          this.selectedAppointment = response.data;
          console.log(this.selectedAppointment);
          if (!this.selectedAppointment.report) {
            this.showAppointmentReport = false;
          } else {
            this.$store.commit("change", this.selectedAppointment);
            this.showAppointmentReport = true;
          }
        });
    },
    closeSelectedReservation: function () {
      this.selectedReservation = null;
      this.showSelectedReservation = false;
    },
    changeChosenPerson(value) {
      this.chosenPatientId = value.id;
    },
    goBack() {
      this.showAppointmentReport = false;
    },
  },
};
</script>


<style scoped>
</style>
