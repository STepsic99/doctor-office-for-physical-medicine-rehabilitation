<template>
  <div style="margin-top: 60px"></div>
  <div class="container" style="margin-bottom: 30px">
    <div v-if="!startReport && !showAppointmentReport"  class="row">
      <div class="col">
        <h1>Kalendar</h1>
        <FullCalendar :options="calendarOptions" />
        <br />
      </div>

              <div v-if="showSelectedAppointment" 
    class="row d-flex justify-content-center"
        style="margin-top: 30px">
       
               <div class="col-8">
          <div class="card">
            <div class="card-body">
              <h4 style="display: inline" class="card-title">
                Započni pregled
              </h4>
              <span style="float:right">{{transformDate(this.selectedAppointment.start)}}</span>
              <br />
             <table style="width: 100%;border-collapse:separate; border-spacing:2em 2em;">
            <tbody>
            <tr>
                <td style="vertical-align: top;text-align:left;padding-left:5em">
                    <div>
                    <img class="img-fluid  thumb" src="../assets/person.jpg" alt="">
                </div>
                </td>
                <td style="text-align:left;">
                    <div>
                <h5 class="mb-0" style="color:#0d6efd;margin-bottom:1em">{{this.selectedAppointment.patientFirstName}} {{this.selectedAppointment.patientLastName}}</h5>
                <i class="fas fa-id-card" aria-hidden="true"></i> {{this.selectedAppointment.patientPersonalID}}
                </div>
                </td>
                <td>
                    <button type="button" class="btn btn-primary" v-on:click="showModal(this.selectedAppointment.patientId)">Istorija pregleda</button>
                </td>
                </tr>

                    <tr>
                         <td style="vertical-align: top;text-align:left;padding-left:5em">
                           Usluge:  
                         </td>
                        <td style="vertical-align: top;text-align:left;">
                            <div>                 
                                <span  v-for="s in this.selectedAppointment.services" :key="s.id" class="badge bg-primary m-1">{{s.name}}</span>                         
                            </div>
                        </td>
                    </tr>
                <tr>
                <td colspan="3" style="text-align:center;">
                       <div>
                <button
              class="btn btn-danger"
              v-on:click="goBack()"
            >
              Zatvori
            </button>
              <button class="btn btn-success" style="margin-left:2em" v-on:click="startAppointment()">
                Započni pregled
              </button>
              </div>
                </td>
                </tr>
            </tbody>
            </table>
             
            </div>
          </div>
        </div>
    </div>
    </div>
    <div v-else-if="startReport">
        <table width="100%" style="margin-left:5em;margin-right:5em">
            <tr>
                <th style="text-align:left" width="25%"><i class="fa fa-arrow-left" aria-hidden="true" style="color:red;cursor:pointer;zoom:1.5" v-on:click="goBack()"></i></th>
                <th width="50%"><h1>{{selectedEvent.extendedProps.services}}</h1></th>
                <th width="25%" style="text-align:right"></th>
            </tr>
            </table>
      <div style="text-align:left;margin-top:5%;margin-bottom:5%;margin-left:5em;margin-right:5em">
        Ime i prezime: <b>{{selectedAppointment.patientFirstName}} {{selectedAppointment.patientLastName}}</b>  <span style="float:right">
       Datum pregleda: {{transformDate(selectedAppointment.start)}}
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

      <div v-else-if="showAppointmentReport">
        <div style="text-align:left;margin-bottom:1em;" width="25%"><i class="fa fa-arrow-left" aria-hidden="true" style="color:red;cursor:pointer;zoom:1.5" v-on:click="goBack()"></i></div>
        <Report />
      </div>
    

   <PatientHistoryModal
      v-show="isModalVisible"
      @close="closeModal"
      v-bind:renderCompHistory="renderCompHistory"
      v-bind:pickedPatientId="pickedPatientId"
    />
 
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
import Report from "@/components/Report.vue"
import PatientHistoryModal from "@/components/PatientHistoryModal.vue";

export default {
  name: "DoctorCalendar",
  components: {
    FullCalendar,
    Datepicker,
    PatientList,
    DiseaseList,
    Report,
    PatientHistoryModal
  },
  data: function () {
    return {
      pickedPatientId: 0,
      isModalVisible: false,
      renderCompHistory: false,
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
      },
      showAppointmentReport:false,
      startReport: false
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
        if(!this.selectedAppointment.report){
          this.showSelectedAppointment = true;
          this.showAppointmentReport = false;
        }
         else{
          this.$store.commit('change',this.selectedAppointment)
          this.showAppointmentReport = true;
         }
      });   
    },
    changeChosenDiseases(value) {
      this.chosenDiseases = value;
      this.searchClosed = true;
    },
    transformDate(rawDate){
        rawDate = new Date(rawDate)
        return rawDate.getDate()+"."+(rawDate.getMonth()+1)+"."+rawDate.getFullYear()+"."+" "+rawDate.getHours()+":"+(rawDate.getMinutes()<10?'0':'') +rawDate.getMinutes()
    },
    goBack(){
        this.showSelectedAppointment = false;
        this.showAppointmentReport = false;
        this.startReport = false;
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
              console.log(response)
              window.location.reload();
        })
        .catch(err => {alert("Neuspešna operacija. Kod greške: "+err.response.status)});
    },
    startAppointment(){
      this.showSelectedAppointment = false;
      this.startReport = true;
    },
    showModal(patientId) {
          this.pickedPatientId = patientId
          this.renderCompHistory = true;
          this.isModalVisible = true;
       
      },
      closeModal() {
        this.renderCompHistory = false;
        this.isModalVisible = false;
      }
  },
};
</script>


<style scoped>

.service-table, table.service-table  th, table.service-table td{
   border: 1px solid black;
  border-collapse: collapse;
}

.thumb {
  
    width: 80px;
    height: 80px;
    -o-object-fit: cover;
    object-fit: cover;
    overflow: hidden;
    border-radius: 20%;
}

</style>
