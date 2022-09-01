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
        
      </div>

      <div
        v-if="editAppointment"
        class="row d-flex justify-content-center"
        style="margin-top: 30px"
      >
        <div class="col-10">
          <div class="card">
            <div class="card-body">
              <h4 style="display: inline" class="card-title">
                Zabeležite terapiju
              </h4>
              <span style="float:right">{{transformDate(this.currentAppointment.start)}}</span>
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
                <h5 class="mb-0" style="color:#0d6efd;margin-bottom:1em">{{this.currentAppointment.patientFirstName}} {{this.currentAppointment.patientLastName}}</h5>
                <i class="fas fa-id-card" aria-hidden="true"></i> {{this.currentAppointment.patientPersonalID}}
                </div>
                </td>
                <td>
                    <button type="button" class="btn btn-primary" v-on:click="showModal()">Vidi izveštaj</button>
                </td>
                </tr>

                    <tr>
                         <td style="vertical-align: top;text-align:left;padding-left:5em">
                           Usluge:  
                         </td>
                        <td style="vertical-align: top;text-align:left;">
                            <div>                 
                                <span  v-for="s in this.currentAppointment.services" :key="s.id" class="badge bg-primary m-1">{{s.name}}</span>                         
                            </div>
                        </td>
                    </tr>

                <tr>
                    <td style="vertical-align: top;text-align:left;padding-left:5em">
                        Pacijent prisutan: 
                    </td>
                    <td>
                        <div style="float:left" class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="inlineRadioOptions" v-model="patientPresent"  value="PRESENT">
                        <label class="form-check-label">Da</label>
                        </div>
                        <div  style="float:right" class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="inlineRadioOptions" v-model="patientPresent"  value="ABSENT">
                        <label class="form-check-label">Ne</label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td style="vertical-align: top;text-align:left;padding-left:5em" >
                        Komentar: 
                    </td>
                    <td>
                       <textarea class="form-control" rows="3" v-model="note"></textarea>
                    </td>
                </tr>
            </tbody>
            </table>

                <button
              class="btn btn-danger m-1"
              v-on:click="hideEditAppointment()"
            >
              Odustani
            </button>
            <button
              :disabled="!createNewAppointment.valid"
              class="btn btn-success m-1"
              v-on:click="changeAppointment()"
            >
                Sačuvaj
            </button>
              
            </div>
          </div>
        </div>
      </div>
    <div v-else-if="showAppointment" 
    class="row d-flex justify-content-center"
        style="margin-top: 30px">
       
               <div class="col-10">
          <div class="card">
            <div class="card-body">
              <h4 style="display: inline" class="card-title">
                Beleške o terapiji
              </h4>
              <span style="float:right">{{transformDate(this.currentAppointment.start)}}</span>
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
                <h5 class="mb-0" style="color:#0d6efd;margin-bottom:1em">{{this.currentAppointment.patientFirstName}} {{this.currentAppointment.patientLastName}}</h5>
                <i class="fas fa-id-card" aria-hidden="true"></i> {{this.currentAppointment.patientPersonalID}}
                </div>
                </td>
                <td>
                    
                </td>
                </tr>

                    <tr>
                         <td style="vertical-align: top;text-align:left;padding-left:5em">
                           Usluge:  
                         </td>
                        <td style="vertical-align: top;text-align:left;">
                            <div>                 
                                <span  v-for="s in this.currentAppointment.services" :key="s.id" class="badge bg-primary m-1">{{s.name}}</span>                         
                            </div>
                        </td>
                    </tr>

                <tr>
                    <td style="vertical-align: top;text-align:left;padding-left:5em">
                        Pacijent prisutan: 
                    </td>
                    <td>
                        <div style="float:left" class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="inlineRadioOptions" v-model="this.currentAppointment.patientPresent"  value="PRESENT">
                        <label class="form-check-label">Da</label>
                        </div>
                        <div  style="float:right" class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="inlineRadioOptions" v-model="this.currentAppointment.patientPresent"  value="ABSENT">
                        <label class="form-check-label">Ne</label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td style="vertical-align: top;text-align:left;padding-left:5em" >
                        Komentar: 
                    </td>
                    <td>
                       <textarea class="form-control" rows="3" v-model="this.currentAppointment.note" disabled></textarea>
                    </td>
                </tr>
                <tr>
               
                <td colspan ='3' style="padding-top:2em">
                       <div>
                <button
              class="btn btn-danger"
              v-on:click="goBack()"
            >
              Zatvori
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

    <ReportModal
      v-show="isModalVisible"
      @close="closeModal"
      v-bind:renderComp="renderComp"
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
import ReportModal from "@/components/ReportModal.vue";

export default {
  name: "PhysiotherapistCalendar",
  components: {
    FullCalendar,
    Datepicker,
    PatientList,
    ReportModal
  },
  data: function () {
    return {
      isModalVisible: false,
      terms: [],
      renderComp: false,

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
      chosenPatientId: "",
      appointments: [],
      showAppointmentReport: false,
      selectedEvent: {},
      selectedAppointment: {},
      cnt: 0,
      selected: null,
      options: [],
      showAppointment:false,
      editAppointment:false,
      patientPresent: null,
      note:'',
      currentAppointment: {}
    };
  },
  mounted: function () {
    this.calendarOptions.select = this.selectInCalendar;
    this.calendarOptions.eventClick = this.eventClickCalendar;
    axios.defaults.headers.common.Authorization =
      "Bearer " + window.sessionStorage.getItem("jwt");
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
    
    },
    subAppointment(a){
      for (var i = 0; i < this.newAppointments.length; i++) {
        if (this.newAppointments[i].id == a.id) {
          this.newAppointments.splice(i, 1);
          break;
        }
      }
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
      if (this.showAppointment || this.editAppointment) return;

      let e = info.event;
      this.loadDataSelectedReservation(e);
    },
    loadDataSelectedReservation: function (event) {
      this.selectedEvent = event;
      axios.defaults.headers.common.Authorization =
        "Bearer " + window.sessionStorage.getItem("jwt");
      axios
        .get(
          "http://localhost:8180/api/v1/physiotherapist-appointments/" +
            event.extendedProps.appID
        )
        .then((response) => {
          this.selectedAppointment = response.data;
          console.log(this.selectedAppointment);
          if (this.selectedAppointment.patientPresent!='UNSET') {
            this.showAppointment = true;
            this.currentAppointment = this.selectedAppointment;
          } else {
            this.currentAppointment = this.selectedAppointment;
            this.editAppointment = true;
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
    hideEditAppointment(){
        this.editAppointment = false;
    },
    changeAppointment(){
        this.currentAppointment.note = this.note;
        this.currentAppointment.patientPresent = this.patientPresent
        axios.defaults.headers.common.Authorization = "Bearer " + window.sessionStorage.getItem("jwt");
        axios
        .put("http://localhost:8180/api/v1/physiotherapist-appointments/"+this.currentAppointment.appID, this.currentAppointment)
        .then((response) =>{
            console.log(response)
            window.location.reload();
        }).catch(err => {alert("Neuspešna operacija. Kod greške: "+err.response.status)});
    },
    goBack(){
        this.showAppointment =false;
    },
     transformDate(rawDate){
        rawDate = new Date(rawDate)
        return rawDate.getDate()+"."+(rawDate.getMonth()+1)+"."+rawDate.getFullYear()+"."+" "+rawDate.getHours()+":"+(rawDate.getMinutes()<10?'0':'') +rawDate.getMinutes()
    },
    showModal() {
        axios.defaults.headers.common.Authorization =
        "Bearer " + window.sessionStorage.getItem("jwt");
      axios
        .get(
          "http://localhost:8180/api/v1/patients/" +
            this.selectedAppointment.patientId+"/last-examination"
        )
        .then((response) => {
          console.log(response.data)
          this.$store.commit("change", response.data);
          this.renderComp = true;
          this.isModalVisible = true;
        });
      },
      closeModal() {
        this.renderComp = false;
        this.isModalVisible = false;
      },
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
