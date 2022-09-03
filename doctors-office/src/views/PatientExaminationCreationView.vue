<template>
  <div style="margin-top: 60px"></div>
  <div class="container" style="margin-bottom: 30px">
    <div v-if="!showAppointmentReport" class="row">
      <div class="col">
        <h1>Kalendar</h1>
        <FullCalendar :options="calendarOptions"/>
        <br />
      </div>
    

    <div class="row d-flex justify-content-center">
      <button
        v-if="!createNewAppointment.formVisible && !showAppointment"
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
              
              <table width="100%" style="margin-top: 2.66em;border-collapse:separate; border-spacing:5em 0;">
                <tr>
                  <td>
                       <Datepicker
                  v-on:click="showcreateNewAppointmentForm()"
                  v-model="createNewAppointment.date"
                  range
                ></Datepicker>
                  </td>
                  <td>
                      <input
                  disabled
                  v-model="createNewAppointment.type"
                  type="text"
                  class="form-control"
                  style="float:right"
                />
                  </td>
                  </tr>
              </table>
               
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
  name: "PatientExaminationCreationView",
  components: {
    FullCalendar,
    Datepicker,
    PatientList,
    Report
  },
  data: function () {
    return {
      terms: [],

      selectedReservation: {},
      showSelectedReservation: false,

      createNewAppointment: {
        date: [],
        formVisible: false,
        type: "PREGLED",
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
        nowIndicator: true,
        slotDuration: "01:00:00",
        displayEventTime: false,
        selectOverlap: false,
        locale: "sr-ME",
        buttonText:{
                    today:    'Danas',
                    month:    'Mesec',
                    week:     'Nedelja',
                    day:      'Dan',
                    list:     'list'
                  },
        allDaySlot: false
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
      showAppointment:false,
      currentAppointment: {}
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
          console.log(a);
          a.display = "auto";
          a.textColor = "white";
          a.backgroundColor = "#dc3545";
          a.borderColor = "#dc3545";
          a.description = "opis";
          a.editable = false;
          a.overlap = false;
    
          if(new Date(a.start).getMinutes() != 0) a.start= (new Date(a.start)).setMinutes(0)
          if(new Date(a.end).getMinutes() != 0) a.end= (new Date(a.end)).setMinutes(59)
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
    addNewAppointment: function () {
      

      const pickedServices = [];
      pickedServices.push(this.createNewAppointment.type);
      axios.defaults.headers.common.Authorization =
        "Bearer " + window.sessionStorage.getItem("jwt");
      axios
        .post("http://localhost:8180/api/v1/doctor-appointments/examination", {
           medicalWorkerID: 1,
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
          )  
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
            this.showAppointment = true;
            this.currentAppointment = this.selectedAppointment;
          } else {
            this.$store.commit("change", this.selectedAppointment);
            this.showAppointmentReport = true;
            this.showAppointment = false;
          }
        });
    },
    changeChosenPerson(value) {
      this.chosenPatientId = value.id;
    },
    goBack(){
        this.showAppointmentReport = false;
        this.showAppointment = false;
    },
    transformDate(rawDate){
        rawDate = new Date(rawDate)
        return rawDate.getDate()+"."+(rawDate.getMonth()+1)+"."+rawDate.getFullYear()+"."+" "+rawDate.getHours()+":"+(rawDate.getMinutes()<10?'0':'') +rawDate.getMinutes()
    },
    cancelAppointment(){
      axios.defaults.headers.common.Authorization =
        "Bearer " + window.sessionStorage.getItem("jwt");
        axios
        .delete("http://localhost:8180/api/v1/doctor-appointments/"+this.currentAppointment.appID)
        .then((response) =>{
              console.log(response)
              window.location.reload();
        })
        .catch(err => {alert("Neuspešna operacija. Kod greške: "+err.response.status)});
    }
  },
};
</script>


<style scoped>
.thumb {
  
    width: 80px;
    height: 80px;
    -o-object-fit: cover;
    object-fit: cover;
    overflow: hidden;
    border-radius: 20%;
}
</style>
