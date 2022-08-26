<template>
  <div style="margin-top: 60px"></div>
  <div class="container" style="margin-bottom: 30px">
    <div v-if="!profileSelected">
      <PatientList :existingPatient="true" @chosenPerson="changeChosenPerson" />
    </div>

    <div v-else class="container">
     
        <div class="row justify-content-between">
          <div class="col-6">
            <div class="card">
              <div class="card-body">
                <label class="form-label">Ime</label>
                <input
                  :disabled="!isEditingProfile"
                  v-model="selectedPatient.firstName"
                  type="text"
                  placeholder=""
                  class="form-control"
                />
                <br />
                <label class="form-label">Prezime</label>
                <input
                  :disabled="!isEditingProfile"
                  v-model="selectedPatient.lastName"
                  type="text"
                  placeholder=""
                  class="form-control"
                />
                <br />
                 <label class="form-label">JMBG</label>
                <input
                  :disabled="!isEditingProfile"
                  v-model="selectedPatient.personalID"
                  type="text"
                  placeholder=""
                  class="form-control"
                />
                <br />
                <label class="form-label">Broj telefona</label>
                <input
                  :disabled="!isEditingProfile"
                  v-model="selectedPatient.phoneNumber"
                  type="text"
                  placeholder=""
                  class="form-control"
                />
                <br />
                <label for="exampleInputEmail1" class="form-label"
                  >Korisničko ime</label
                >
                <input
                  :disabled="!isEditingProfile"
                  v-model="selectedPatient.username"
                  type="email"
                  class="form-control"
                />
                <br /><br />

        

                <div class="d-flex justify-content-center">
                  <button
                    v-if="!isEditingProfile"
                    v-on:click="editProfile()"
                    class="btn btn-primary m-1"
                  >
                    Izmenite podatke
                  </button>
                  <button
                    v-if="isEditingProfile"
                    v-on:click="cancelEditProfile()"
                    class="btn btn-danger m-1"
                  >
                    Odustani
                  </button>
                  <button
                    v-if="isEditingProfile"
                    v-on:click="saveEditProfile()"
                    class="btn btn-primary m-1"
                  >
                    Sačuvaj
                  </button>
                </div>
              </div>
            </div>
            <br />


       

            
          </div>
          <div class="col-4" style="padding-top:5%">
            <img src="../assets/person.jpg" class="img-fluid" />
           
          </div>
        </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import shared from "../shared";

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
  name: "PatientsProfiles",
  components: {
    PatientList,
  },
  data: function () {
    return {
      isEditingProfile: false,
      profileSelected: false,
      selectedPatientCopy: {}
    };
  },
  mounted: function () {},
  methods: {
    changeChosenPerson(value) {
      this.selectedPatient = value;
      this.profileSelected = true;
    },
     editProfile: function(){
          this.selectedPatientCopy = JSON.parse(JSON.stringify(this.selectedPatient));
          this.isEditingProfile = true;
      },
      cancelEditProfile: function(){
          this.selectedPatient = this.selectedPatientCopy;
          this.isEditingProfile = false;
      },
      saveEditProfile: function(){
        this.isEditingProfile = false;
        axios.defaults.headers.common.Authorization =
        "Bearer " + window.sessionStorage.getItem("jwt");
        axios
        .put("http://localhost:8180/api/v1/patients", this.selectedPatient)
        .then((response) =>{
              console.log("Success")
        })
        .catch(err => {alert("Neuspešna operacija. Kod greške: "+err.response.status)});
      }
  },
};
</script>


<style scoped>
</style>
