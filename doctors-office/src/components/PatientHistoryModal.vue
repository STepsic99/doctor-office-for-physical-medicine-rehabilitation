<template>
<transition name="modal-fade">
  <div v-if="renderCompHistory && renderContent" centered class="modal-backdrop">
    <div class="modal">
      <header class="modal-header">
        <slot name="header">
            Profil
        </slot>
        <button
          type="button"
          class="btn-close"
          v-on:click="close()"
        >
          x
        </button>
      </header>

<section class="modal-body">
<slot name="body">
             
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
          <div class="col-6">
            <div class="card">
              <div v-if="!showMedicalHistory" class="card-body">
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
                    v-if="!isEditingProfile && isNurse()"
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


              <div v-else class="card-body">
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
            <br />  

            
          </div>
          <div class="col-4" style="padding-top:5%">
            <img src="../assets/person.jpg" class="img-fluid" />
           
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
    

</slot>
</section>


    </div>
  </div>
</transition>
</template>
<script>
 import 'vue3-date-time-picker/dist/main.css'
 import ReportModal from "@/components/ReportModal.vue";
import CommentModal from "@/components/CommentModal.vue";
import shared from '../shared';
import axios from "axios";
export default {
  name: "PatientHistoryModal",
  props: ['renderCompHistory','pickedPatientId'],
  components: {
    ReportModal,
    CommentModal
  },
  data: function () {
    return {
     isEditingProfile: false,
      selectedPatientCopy: {},
      showMedicalHistory: true,
      appointments: [],
      isModalVisible: false,
      renderComp: false,
      isModalVisibleTherapy: false,
      renderCompTherapy: false,
      renderContent: false
    };
  },
  watch: {
    renderCompHistory: function (val) {
        if (!val) return;
        axios.defaults.headers.common.Authorization =
        'Bearer ' + window.sessionStorage.getItem('jwt')
      axios
        .get('http://localhost:8180/api/v1/patients/'+this.pickedPatientId)
        .then((response) => {
          this.selectedPatient = response.data
          this.getMedicalHistory();
        }).catch(err => {alert("Neuspešna operacija. Kod greške: "+err.response.status)});
    }
    },
  methods: {
    getMedicalHistory: function () {
      axios.defaults.headers.common.Authorization =
      "Bearer " + window.sessionStorage.getItem("jwt");
      axios
      .get("http://localhost:8180/api/v1/patients/"+this.selectedPatient.id+"/appointments/medical-documentation")
      .then((response) =>{
            this.appointments = response.data;
            this.renderContent = true;
      })
      .catch(err => {alert("Neuspešna operacija. Kod greške: "+err.response.status)});
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
      },
      isNurse(){
            if(shared.getRoleFromToken()==='ROLE_NURSE') return true;
            return false;
      },
    close() {
        this.renderContent = false;
      this.$emit('close');
    }
  }
}
</script>
<style scoped>




  .modal-backdrop {
      
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(0, 0, 0, 0.3);
    display: flex;
    justify-content: center;
    align-items: center;
    
  }

  .modal {
    background: #FFFFFF;
    box-shadow: 2px 2px 20px 1px;
    overflow-x: auto;
    display: flex;
    flex-direction: column;
    height: 99%;
    width: 90%;
     position: relative;
  top: 49.5%;
  transform: translateY(-50%);
  
    
    
  }

  .modal-header,
  .modal-footer {
    padding: 15px;
    display: flex;
  }

  .modal-header {
    position: relative;
    border-bottom: 1px solid #eeeeee;
    color: black;
    justify-content: space-between;
    justify-content: center;
    align-items: center;
    font-weight: bold;
   
  }

  .modal-footer {
    border-top: 1px solid #eeeeee;
    flex-direction: column;
    justify-content: flex-end;
  }

  .modal-body {
    position: relative;
    padding: 20px 10px;
  }

  .btn-close {
    position: absolute;
    top: 0;
    right: 0;
    border: none;
    font-size: 20px;
    padding: 10px;
    cursor: pointer;
    font-weight: bold;
    color: #0d6efd;
    background: transparent;
  }

  .btn-green {
    color: white;
    background: #4AAE9B;
    border: 1px solid #4AAE9B;
    border-radius: 2px;
  }

  .modal-fade-enter {
    opacity: 0;
  }
  .modal-fade-leave-to {
    opacity: 0;
  }

  .modal-fade-enter-active .modal-fade-leave-active {
    transition: opacity .5s ease;
  }
  .modal-fade-leave-active {
    transition: opacity .5s ease;
  }
  
</style>