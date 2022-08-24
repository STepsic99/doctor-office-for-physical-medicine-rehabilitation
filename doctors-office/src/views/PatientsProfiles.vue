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
                  disabled
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
                    Cancel
                  </button>
                  <button
                    v-if="isEditingProfile"
                    v-on:click="saveEditProfile()"
                    class="btn btn-primary m-1"
                  >
                    Save
                  </button>
                </div>
              </div>
            </div>
            <br />

            <!-- Izmena passworda -->
            <div class="card">
              <div class="card-body">
                <div v-if="isEditingPassword">
                  <input
                    v-model="passwordData.oldPassword"
                    type="password"
                    placeholder="Your old password"
                    class="form-control"
                  /><br />
                  <input
                    v-model="passwordData.newPassword"
                    type="password"
                    placeholder="Your new password"
                    class="form-control"
                  /><br />
                  <input
                    v-model="passwordData.newPasswordRepeated"
                    type="password"
                    placeholder="Repeat Your new password"
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
                    Change password
                  </button>
                  <button
                    v-if="isEditingPassword"
                    v-on:click="cancelEditPassword()"
                    class="btn btn-danger m-1"
                  >
                    Cancel
                  </button>
                  <button
                    v-if="isEditingPassword"
                    v-on:click="saveEditPassword()"
                    class="btn btn-primary m-1"
                  >
                    Save
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
import shared from "../shared";

import Datepicker from "vue3-date-time-picker";
import "vue3-date-time-picker/dist/main.css";

// FULL CALNEDAR
import "@fullcalendar/core/vdom"; // solves problem with Vite
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
      profile: {
        loyalty: {},
        email: "",
        password: "",
        firstName: "",
        lastName: "",
        phoneNumber: "",
        address: {
          country: "",
          city: "",
          street: "",
          number: "",
          latitude: 0,
          longitude: 0,
        },
      },
      profileSelected: false,
    };
  },
  mounted: function () {},
  methods: {
    changeChosenPerson(value) {
      this.selectedPatient = value;
      this.profileSelected = true;
    }
  },
};
</script>


<style scoped>
</style>
