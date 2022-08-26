<template>
  <div style="margin-top: 60px"></div>
  <div class="container" style="margin-bottom: 30px">
    <div class="row">
      <div v-if="!showSelectedAppointment" class="col">
        <h1>Calendar</h1>
        <FullCalendar :options="calendarOptions" />
        <br />
      </div>

    <div v-else>
        <table width="100%">
            <tr>
                <th width="25%"><i class="fa fa-arrow-left" aria-hidden="true" style="color:red;cursor:pointer" v-on:click="goBack()"></i></th>
                <th width="50%"><h1>{{selectedAppointment.extendedProps.services}}</h1></th>
                <th width="25%">{{transformDate(selectedAppointment.start)}} - {{transformDate(selectedAppointment.end)}}</th>
            </tr>
            </table>
     <!-- <div class="col">
       <h1 style="display: inline">{{selectedAppointment.extendedProps.services}}</h1>
      </div>
      <div class="col">
       <h6 style="display: inline">{{transformDate(selectedAppointment.start)}} - {{transformDate(selectedAppointment.end)}}</h6>
      </div> -->
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

export default {
  name: "DoctorCalendar",
  components: {
    FullCalendar,
    Datepicker,
    PatientList,
  },
  data: function () {
    return {
      terms: [],

      selectedAppointment: {},
      showSelectedAppointment: false,

      createNewAppointment: {
        date: [],
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
                a.title=a.services
                this.calendarOptions.events.push(a);
            }
      });
      
  },
  methods: {
    showcreateNewAppointmentForm: function () {
      this.createNewAppointment.msg = "";
      this.createNewAppointment.valid = true;
    },
    selectInCalendar: function (selectedDate) {
      if (this.showSelectedAppointment) return;
      this.createNewAppointment.msg = "";
      this.createNewAppointment.valid = true;
      this.createNewAppointment.date[0] = new Date(selectedDate.start);
      this.createNewAppointment.date[1] = new Date(
        new Date(selectedDate.end) - 1000
      );
      if (this.overlap()) {
        this.createNewAppointment.msg =
          "Error, new term have overlap with other term";
        this.createNewAppointment.valid = false;
      }
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

      let e = info.event;
      this.loadDataSelectedReservation(e);
    },
    loadDataSelectedReservation: function (event) {
      this.selectedAppointment = event;
      this.showSelectedAppointment = true;
      console.log(event)
    },
    closeSelectedReservation: function () {
      this.selectedAppointment = null;
      this.showSelectedAppointment = false;
    },
    changeChosenPerson(value) {
      this.chosenPatientId = value.id;
    },
    transformDate(rawDate){
        return rawDate.getDate()+"."+rawDate.getMonth()+"."+rawDate.getFullYear()+"."+" "+rawDate.getHours()+":"+(rawDate.getMinutes()<10?'0':'') +rawDate.getMinutes()
    },
    goBack(){
        this.showSelectedAppointment = false;
    }
  },
};
</script>


<style scoped>
</style>
