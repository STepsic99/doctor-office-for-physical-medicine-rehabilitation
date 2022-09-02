<template>
  <div style="margin-top: 60px"></div>
  <div class="container" style="margin-bottom: 30px">
    

    <div class="container">

      <div style="text-align:left;margin-bottom: 30px">

      <div
              style=" float:left;margin-bottom:0"
              class="form-check form-switch"
            >
              <input
                v-model="showMedicalHistory"
                class="form-check-input"
                type="checkbox"
              />
            </div>
            <br />
            <div
              style=" font-style: italic; zoom: 0.8; text-align:left;margin-top:0.5em"
              v-if="showMedicalHistory"
            >
              Medicinska istorija
            </div>
            <div style=" font-style: italic; zoom: 0.8;text-align:left;margin-top:0.5em" v-else>
              Lični podaci
            </div>

     </div>
     
        <div class="row justify-content-between">
          <div v-if="!showMedicalHistory" class="col-6">
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

        <div v-else class="col-6">
          <div class="card">
                <div class="card-body">
               <table width="100%" style="border-collapse:separate; border-spacing:0 2em;">
                <tr>
                  <th>Usluga</th>
                  <th>Datum</th>
                  <th>Prisutnost</th>
                  <th>Dokumentacija</th>
                </tr>
                <tr v-for="a in appointments" :key="a.id">
                  <td>
                    <span v-for="service in a.services" :key="service.id" class="badge bg-primary m-1">
                     {{service.name}}
                      </span>
                    </td>
                    <td>
                      {{transformDate(a.start)}}
                    </td>
                   
                      <td v-if="a.type=='PHYSIOTHERAPIST'">
                        <span>
                        <i v-if="a.patientPresent=='UNSET'" class="fa fa-question"></i>
                        <i v-else-if="a.patientPresent=='PRESENT'" class="fa fa-check" aria-hidden="true" style="color:green;"></i>
                        <i v-else-if="a.patientPresent=='ABSENT'" class="fa fa-times" aria-hidden="true" style="color:red;"></i>
                        </span>
                        </td>
                      <td v-if="a.type=='PHYSIOTHERAPIST'">
                        <span v-if="a.note" style="color:#0d6efd;text-decoration: underline;cursor:pointer" v-on:click="showModalTherapy(a)">
                        Vidi komentar
                        </span>
                      </td>
                   
                    
                      <td v-if="a.type=='DOCTOR'">
                        <span>
                        <i v-if="presenceAtExamination(a)=='UNSET'" class="fa fa-question"></i>
                        <i v-else-if="presenceAtExamination(a)=='PRESENT'" class="fa fa-check" aria-hidden="true" style="color:green;"></i>
                        <i v-else-if="presenceAtExamination(a)=='ABSENT'" class="fa fa-times" aria-hidden="true" style="color:red;"></i>
                        </span>
                      </td>
                      <td v-if="a.type=='DOCTOR'">
                        <span v-if="a.report" style="color:#0d6efd;text-decoration: underline;cursor:pointer" v-on:click="showModal(a)">
                        Vidi izveštaj
                        </span>
                      </td>    
                  </tr>
               </table>
              </div>
          </div>
        </div>

          <div class="col-4" style="padding-top:5%">
            <img src="../assets/person.jpg" class="img-fluid" />
           
          </div>
        </div>
    </div>

     <ReportModal
      v-show="isModalVisible"
      @close="closeModal"
      v-bind:renderComp="renderComp"
    />
    <CommentModal
      v-show="isModalVisibleTherapy"
      @close="closeModalTherapy"
      v-bind:renderComp="renderCompTherapy"
    />
  </div>
</template>

<script>
import axios from "axios";

import "vue3-date-time-picker/dist/main.css";


import "@fullcalendar/core/vdom"; 

import shared from '@/shared';
import ReportModal from "@/components/ReportModal.vue";
import CommentModal from "@/components/CommentModal.vue";

export default {
  name: "PatientProfile",
  components: {
    ReportModal,
    CommentModal
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
      newPasswordRepeated: '',
      showMedicalHistory: false,
        isModalVisible: false,
      renderComp: false,
      isModalVisibleTherapy: false,
      renderCompTherapy: false,
      appointments: []
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
  watch: {
    showMedicalHistory: function (val) {
      if(!val) return;
      axios.defaults.headers.common.Authorization =
      "Bearer " + window.sessionStorage.getItem("jwt");
      axios
      .get("http://localhost:8180/api/v1/patients/"+this.selectedPatient.id+"/appointments/medical-documentation")
      .then((response) =>{
            this.appointments = response.data;
      })
      .catch(err => {alert("Neuspešna operacija. Kod greške: "+err.response.status)});
    }
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
      },
      transformDate(rawDate){
        rawDate = new Date(rawDate)
        return rawDate.getDate()+"."+(rawDate.getMonth()+1)+"."+rawDate.getFullYear()+"."+" "+rawDate.getHours()+":"+(rawDate.getMinutes()<10?'0':'') +rawDate.getMinutes()
    },
    presenceAtExamination(app){
        if(app.report) return 'PRESENT'
        else {
          if(app.end < (new Date())) return 'ABSENT'
          return 'UNSET'
        }
    },
    showModal(app) {
        this.$store.commit("change", app);
        this.renderComp = true;
        this.isModalVisible = true;
      
    },
    closeModal() {
      this.renderComp = false;
      this.isModalVisible = false;
    },
    showModalTherapy(app) {
        this.$store.commit("change", app);
        this.renderCompTherapy = true;
        this.isModalVisibleTherapy = true;
      
    },
    closeModalTherapy() {
      this.renderCompTherapy = false;
      this.isModalVisibleTherapy = false;
    }
  },
};
</script>


<style scoped>
</style>
