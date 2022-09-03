<template>
  <div style="margin-top: 60px"></div>
  <div class="container" style="margin-bottom: 30px">
    <div class="row">
      <div class="col">
        <h1>Kalendar</h1>
        <FullCalendar :options="calendarOptions" />
        <br />
      </div>
    </div>


  </div>
</template>

<script>
import axios from "axios";
import shared from '../shared';

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
  name: "PatientCalendar",
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
        hiddenDays: [ 0],
        businessHours: [
            {
              daysOfWeek: [ 6 ],
              startTime: '08:00', 
              endTime: '13:00',
            },
            {
              daysOfWeek: [ 1,2,3,4,5 ],
              startTime: '08:00', 
              endTime: '16:00',
            } 
        ],
        slotMinTime: "08:00:00",
        slotMaxTime: "16:00:00",
        contentHeight: 'auto',
        nowIndicator: true,
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
        console.log(shared.getIdFromToken())
      axios
      .get("http://localhost:8180/api/v1/patients/"+shared.getIdFromToken()+"/appointments")
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
