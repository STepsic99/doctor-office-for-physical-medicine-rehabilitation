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
        <table width="100%" style="margin-left:5em;margin-right:5em">
            <tr>
                <th style="text-align:left" width="25%"><i class="fa fa-arrow-left" aria-hidden="true" style="color:red;cursor:pointer" v-on:click="goBack()"></i></th>
                <th width="50%"><h1>{{selectedEvent.extendedProps.services}}</h1></th>
                <th width="25%" style="text-align:right"></th>
            </tr>
            </table>
      <div style="text-align:left;margin-top:5%;margin-bottom:5%;margin-left:5em;margin-right:5em">
        Ime i prezime: <b>{{selectedAppointment.patientFirstName}} {{selectedAppointment.patientLastName}}</b>  <span style="float:right">
       Datum pregleda: {{transformDate(selectedEvent.start)}}
      </span>
      <br>
        JMBG: {{selectedAppointment.patientPersonalID}}
      </div>
      <h3 style="margin-left:5em;"> IZVEŠTAJ LEAKRA SPECIJALISTE FIZIKALNE MEDICINE </h3>
      <table width="100%" style="margin-top:5%;border-collapse:separate; border-spacing:5em 0;margin-bottom:5%">
        <tr>
          <td style="text-align:left;vertical-align: top;padding-bottom:5em" width="10%">Anamneza: </td>
          <td style="padding-bottom:5em" width="90%"><textarea class="form-control" rows="6" v-model="this.report.anamnesis"></textarea></td>
        </tr>
        <tr>
          <td style="text-align:left;vertical-align: top;padding-bottom:5em" width="10%">Status: </td>
          <td style="padding-bottom:5em" width="90%"><textarea class="form-control" rows="6" v-model="this.report.status"></textarea></td>
        </tr>
        <tr>
          <td style="text-align:left;vertical-align: top;padding-bottom:5em" width="10%">Nalazi: </td>
          <td style="padding-bottom:5em" width="90%"><textarea class="form-control" rows="4" v-model="this.report.medicalFindings"></textarea></td>
        </tr>
        <tr>
          <td style="text-align:left;vertical-align: top;padding-bottom:5em" width="10%">Plan lečenja: </td>
          <td style="padding-bottom:5em" width="90%"><textarea class="form-control" rows="4" v-model="this.report.treatmentPlan"></textarea></td>
        </tr>
        <tr>
          <td style="text-align:left;vertical-align: top;padding-bottom:5em" width="10%">Kontrola: </td>
          <td style="padding-bottom:5em" width="90%"><textarea class="form-control" rows="4" v-model="this.report.followUpExamination"></textarea></td>
        </tr>
        <tr>
          <td style="text-align:left;" width="10%">Dijagnoze: </td>
          <td width="90%" style="text-align:left;"><i style="zoom:2;cursor:pointer;color:green;" class="fa fa-plus" aria-hidden="true" v-on:click="openSearch()"></i></td>
        </tr>
        <tr>
          <td colspan="2" style="text-align:left;"><DiseaseList v-bind:closeSearch="searchClosed" @chosenDisease="changeChosenDiseases"/></td>
        </tr>
        <tr>
          <td style="text-align:left;" width="10%">Usluge: </td>
          <td width="90%" style="text-align:left;"><i style="zoom:2;cursor:pointer;color:green;" class="fa fa-plus" aria-hidden="true" v-on:click="addService()"></i></td>
        </tr>
        </table>
        <div v-if="assignedServices.length!=0" style="text-align:center;background-color:#e9ecef;margin-left:5em;margin-right:5em"><h5 style="margin-bottom:0"><b>NALOG ZA PRUŽANJE TERAPIJE</b></h5></div>
        <div v-if="assignedServices.length!=0" style="margin-right:5em;margin-left:5em;margin-top:1em;margin-bottom:5%;">
        <table class="service-table" width="100%">
          <tr style="background-color:#e9ecef;">
            <th>Naziv usluge</th>
            <th>Napomena</th>
            <th></th>
            </tr>
            <tr v-for="a in assignedServices" :key="a.id">
              <td>
                <input v-model="a.serviceName" class="form-control" list="datalistOptions" style="border:none;border-radius:0" placeholder="Počnite da kucate za pretragu...">
              <datalist id="datalistOptions">
                <option v-for="ser in services" :key="ser.id" :value="ser.name"/>
              </datalist>
              </td>
              <td>
                <input type="text" style="border:none;border-radius:0" class="form-control" v-model="a.note">
              </td>
              <td>
                <i
            class="fa fa-times"
            aria-hidden="true"
            style="color: red; zoom: 2; cursor: pointer"
            v-on:click="subService(a)"
          ></i>
              </td>
              </tr>
        </table>
        </div>
      <button type="button" class="btn btn-primary" v-on:click="finishAppointment()">Završi pregled</button>
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
import DiseaseList from "@/components/DiseaseList.vue"

export default {
  name: "DoctorCalendar",
  components: {
    FullCalendar,
    Datepicker,
    PatientList,
    DiseaseList
  },
  data: function () {
    return {
      terms: [],

      selectedEvent: {},
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
      appointments:[],
      searchClosed:true,
      chosenDiseases:[],
      assignedServices:[],
      assignedService:{
        service:{},
        note:''
      },
      cnt: 0,
      report:{
        anamnesis: '',
        status: '',
        medicalFindings: '',
        treatmentPlan: '',
        followUpExamination: '',
        diagnoses: [],
        therapies: []
      }
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
      this.selectedEvent = event;
      axios.defaults.headers.common.Authorization =
        "Bearer " + window.sessionStorage.getItem("jwt");
      axios
      .get("http://localhost:8180/api/v1/doctor-appointments/"+event.extendedProps.appID)
      .then((response) => {
        this.selectedAppointment = response.data;
        console.log(this.selectedAppointment)
         this.showSelectedAppointment = true;
      });   
    },
    closeSelectedReservation: function () {
      this.selectedEvent = null;
      this.showSelectedAppointment = false;
    },
    changeChosenDiseases(value) {
      this.chosenDiseases = value;
      this.searchClosed = true;
    },
    transformDate(rawDate){
        return rawDate.getDate()+"."+rawDate.getMonth()+"."+rawDate.getFullYear()+"."+" "+rawDate.getHours()+":"+(rawDate.getMinutes()<10?'0':'') +rawDate.getMinutes()
    },
    goBack(){
        this.showSelectedAppointment = false;
    },
    openSearch(){
      this.searchClosed = false;
    },
    addService(){
      if(this.cnt==0){
        axios
        .get("http://localhost:8180/api/v1/services/physiotherapist")
        .then((response) => {
        this.services = response.data;
      });
      }
      this.cnt++;
      this.assignedServices.push({id:this.cnt, serviceName:'',service:{} , note:''})
    },
    subService(a){
      for (var i = 0; i < this.assignedServices.length; i++) {
        if (this.assignedServices[i].id == a.id) {
          this.assignedServices.splice(i, 1);
          break;
        }
      }
    },
    finishAppointment(){
      this.report.diagnoses = this.chosenDiseases;
      for(let i = 0; i<this.assignedServices.length; i++){
          for(let j = 0; j<this.services.length; j++){
            if(this.assignedServices[i].serviceName == this.services[j].name){
              this.assignedServices[i].service = this.services[j]
              break;
            }
          }
      }
      this.report.therapies = this.assignedServices;
      axios.defaults.headers.common.Authorization =
        "Bearer " + window.sessionStorage.getItem("jwt");
        axios
        .post("http://localhost:8180/api/v1/doctor-appointments/"+this.selectedAppointment.appID+"/report", this.report)
        .then((response) =>{
              console.log(response.data)
        })
        .catch(err => {alert("Neuspešna operacija. Kod greške: "+err.response.status)});
    }
  },
};
</script>


<style scoped>

.service-table, table.service-table  th, table.service-table td{
   border: 1px solid black;
  border-collapse: collapse;
}

</style>
