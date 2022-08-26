<template>
  <div style="margin-top: 60px"></div>
  <div class="container" style="margin-bottom: 30px">
    

    <div class="container">
     
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
            
        

              </div>
            </div>
            <br />

            
            <div class="card">
              <div class="card-body">
                <div v-if="isEditingPassword">
                  <input
                    v-model="passwordData.oldPassword"
                    type="password"
                    placeholder="Stara lozinka"
                    class="form-control"
                  /><br />
                  <input
                    v-model="passwordData.newPassword"
                    type="password"
                    placeholder="Nova lozinka"
                    class="form-control"
                  /><br />
                  <input
                    v-model="newPasswordRepeated"
                    type="password"
                    placeholder="Ponovljena nova lozinka"
                    class="form-control"
                    v-bind:class="{ 'is-invalid': !newPassEqual() }"
                  /><br />
                </div>

                <div class="d-flex justify-content-center">
                  <button
                    v-if="!isEditingPassword"
                    v-on:click="editPassword()"
                    class="btn btn-primary m-1"
                  >
                    Promena lozinke
                  </button>
                  <button
                    v-if="isEditingPassword"
                    v-on:click="cancelEditPassword()"
                    class="btn btn-danger m-1"
                  >
                    Odustani
                  </button>
                  <button
                    v-if="isEditingPassword"
                    v-on:click="saveEditPassword()"
                    class="btn btn-primary m-1"
                  >
                    Sačuvaj
                  </button>
                </div>
              </div>
            </div>
            <br />
            <br />
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

import "vue3-date-time-picker/dist/main.css";


import "@fullcalendar/core/vdom"; 

import PatientList from "@/components/PatientList.vue";
import shared from '@/shared';

export default {
  name: "PatientProfile",
  components: {
    PatientList,
  },
  data: function () {
    return {
      isEditingProfile: false,
      selectedPatientCopy: {},
      selectedPatient: {},
      isEditingPassword: false,
      passwordData:{
            oldPassword: '',
            newPassword: ''
        },
      newPasswordRepeated: ''
    };
  },
  mounted: function () {
    axios.defaults.headers.common.Authorization =
        "Bearer " + window.sessionStorage.getItem("jwt");
        axios
        .get("http://localhost:8180/api/v1/patients/"+shared.getIdFromToken())
        .then((response) =>{
              this.selectedPatient = response.data;
        })
        .catch(err => {alert("Neuspešna operacija. Kod greške: "+err.response.status)});
  },
  methods: {
    editPassword: function(){
          this.isEditingPassword = true;
      },
      cancelEditPassword: function(){
          this.passwordData.oldPassword = '';
          this.passwordData.newPassword = '';
          this.newPasswordRepeated = '';
          this.isEditingPassword = false;
      },
      saveEditPassword: function(){
          if(!this.newPassEqual()) 
          {
            alert("Nove lozinke se ne podudaraju.")
            return;
          }
          this.isEditingPassword = false;
          axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
          axios.put('http://localhost:8180/api/v1/users/updatePassword',this.passwordData)
          .then(response => {
                  if (response.data) {
                        window.sessionStorage.clear();
                        this.$router.push('/');
                        alert('Uspesno promenjena lozinka.');
                  }
                  else 
                    alert('Neuspela promena lozinke.')
          }).catch(err => {alert("Neuspešna operacija. Kod greške: "+err.response.status)});
      },
      newPassEqual: function(){
          return this.passwordData.newPassword === this.newPasswordRepeated;
      }
  },
};
</script>


<style scoped>
</style>
