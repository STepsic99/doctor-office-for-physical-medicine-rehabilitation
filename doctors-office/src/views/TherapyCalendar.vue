<template>
  <div style="margin-top: 60px"></div>
  <div class="container" style="margin-bottom: 30px">
    <div class="row">
      <div class="col">
        <h1>Kalendar terapija</h1>
        <FullCalendar :options="calendarOptions" />
        <br />
      </div>

      <div class="row d-flex justify-content-center">
        <button
          v-if="!createNewAppointment.formVisible && !showAppointment"
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
              <br />
              <div>
                <table width="100%" style="border-collapse:separate; border-spacing:1em 1em">
                    <tr>
                        <td colspan="3"><PatientList
                  :existingPatient="oldPatient"
                  @chosenPerson="changeChosenPerson"
                /></td>
                    </tr>
                  <tr v-for="app in newAppointments" :key="app.id">
                    <td width="35%">
                      <Datepicker
                        v-on:click="showcreateNewAppointmentForm()"
                        v-model="app.date"
                        range
                      ></Datepicker>
                    </td>
                    <td width="50%">
                        <VueMultiselect
                        v-model="app.services"
                        :options="services"
                        :multiple="true"
                        :close-on-select="true"
                        label="name"
                        track-by="name">
                        </VueMultiselect>
                    </td>
                    <td style="text-align:right" width="5%">
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
                  <button v-if="nonPassedAppointment" type="button" class="btn btn-danger" v-on:click="cancelAppointment()">Otkaži terapiju</button>  
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

                <tr v-if="!nonPassedAppointment">
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
                <tr v-if="!nonPassedAppointment">
                    <td style="vertical-align: top;text-align:left;padding-left:5em" >
                        Komentar: 
                    </td>
                    <td>
                       <textarea class="form-control" rows="3" v-model="this.currentAppointment.note" disabled></textarea>
                    </td>
                </tr>
                <tr>
               
                <td v-if="!nonPassedAppointment" colspan ='3' style="padding-top:2em">
                       <div>
                <button
              class="btn btn-danger"
              v-on:click="goBack()"
            >
              Zatvori
            </button>
              </div>
                </td>
                
                <td v-if="nonPassedAppointment"></td>
                <td v-if="nonPassedAppointment" style="text-align:left;padding-left:3em;padding-top:2em">
                       <div>
                <button
              :class="[nonPassedAppointment ? 'btn btn-primary' : 'btn btn-danger']"
              v-on:click="goBack()"
            >
              Zatvori
            </button>
              </div>
                </td>
                <td v-if="nonPassedAppointment"></td>
                </tr>
            </tbody>
            </table>
             
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
import VueMultiselect from 'vue-multiselect'

export default {
  name: "TherapyCalendar",
  components: {
    FullCalendar,
    Datepicker,
    PatientList,
    Report,
    VueMultiselect 
  },
  data: function () {
    return {
      terms: [],

      selectedReservation: {},
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
      selectedEvent: {},
      selectedAppointment: {},
      cnt: 0,
      selected: null,
      options: [],
      showAppointment: false,
      currentAppointment: {},
      nonPassedAppointment: false
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
      if(this.newAppointments.length == 0){
          this.newAppointments.push({id:this.cnt, date:[new Date(selectedDate.start),new Date(new Date(selectedDate.end) - 1000)], services:[]})
      } else{
         this.newAppointments.push({id:this.cnt, date:[new Date(selectedDate.start),new Date(new Date(selectedDate.end) - 1000)], services:this.newAppointments[this.newAppointments.length-1].services})
      }     
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
      const tempAppointments=[]
      for(let i = 0; i<this.newAppointments.length;i++){
        tempAppointments.push(
            {startTime: new Date(
                Date.UTC(
                  this.newAppointments[i].date[0].getFullYear(),
                  this.newAppointments[i].date[0].getMonth(),
                  this.newAppointments[i].date[0].getDate(),
                  this.newAppointments[i].date[0].getHours(),
                  this.newAppointments[i].date[0].getMinutes(),
                  this.newAppointments[i].date[0].getSeconds()
                )
              ),
              endTime: new Date(
                Date.UTC(
                  this.newAppointments[i].date[1].getFullYear(),
                  this.newAppointments[i].date[1].getMonth(),
                  this.newAppointments[i].date[1].getDate(),
                  this.newAppointments[i].date[1].getHours(),
                  this.newAppointments[i].date[1].getMinutes(),
                  this.newAppointments[i].date[1].getSeconds()
                )
              ),
            services: this.newAppointments[i].services
            }           
            )
      }
      axios.defaults.headers.common.Authorization =
        "Bearer " + window.sessionStorage.getItem("jwt"); 
      axios
        .post("http://localhost:8180/api/v1/appointments", {
          patientID: this.chosenPatientId,
          medicalWorkerID: 2,
          appointments: tempAppointments
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
          "http://localhost:8180/api/v1/physiotherapist-appointments/" +
            event.extendedProps.appID
        )
        .then((response) => {
          this.selectedAppointment = response.data;
          console.log(this.selectedAppointment);
          if (this.selectedAppointment.patientPresent!='UNSET') {
            this.showAppointment = true;
            this.currentAppointment = this.selectedAppointment;
            this.nonPassedAppointment = false;
          } else {
            this.currentAppointment = this.selectedAppointment;
            this.nonPassedAppointment = true;
            this.showAppointment = true;
          }
        });
    },
    changeChosenPerson(value) {
      this.chosenPatientId = value.id;
    },
    goBack() {
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
        .delete("http://localhost:8180/api/v1/physiotherapist-appointments/"+this.currentAppointment.appID)
        .then((response) =>{
              console.log(response)
              window.location.reload();
        })
        .catch(err => {alert("Neuspešna operacija. Kod greške: "+err.response.status)});
    }
  },
};
</script>

<style src="vue-multiselect/dist/vue-multiselect.css"></style>
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
