import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from '../views/Login.vue'
import shared from '../shared'
import AppointmentCalendar from '../views/AppointmentCalendar.vue'
import PatientCalendar from '../views/PatientCalendar.vue'
import PatientsProfiles from '../views/PatientsProfiles.vue'
import PatientProfile from '../views/PatientProfile.vue'
import DoctorCalendar from '../views/DoctorCalendar.vue'
import TherapyCalendar from '../views/TherapyCalendar.vue'
import PhysiotherapistCalendar from '../views/PhysiotherapistCalendar.vue'
import ServicesView from '../views/ServicesView'
import AboutView from '../views/AboutView'
import ContactView from '../views/ContactView'
import PatientExaminationCreationView from '../views/PatientExaminationCreationView'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/services',
    name: 'services',
    component: ServicesView
  },
  {
    path: '/about',
    name: 'about',
    component: AboutView
  },
  {
    path: '/contact',
    name: 'contact',
    component: ContactView
  },
  { path: '/login', name: 'Login', component: Login },
  {
    path: '/calendar',
    name: 'appointmentCalendar',
    component: AppointmentCalendar,
    meta: { requiresAuth: true, roles: ["ROLE_NURSE"] },
  },
  {
    path: '/patient/calendar',
    name: 'patientCalendar',
    component: PatientCalendar,
    meta: { requiresAuth: true, roles: ["ROLE_PATIENT"] },
  },
  {
    path: '/patient-profiles',
    name: 'PatientsProfiles',
    component: PatientsProfiles,
    meta: { requiresAuth: true, roles: ["ROLE_NURSE","ROLE_DOCTOR","ROLE_PHYSIOTHERAPIST"] },
  },
  {
    path: '/patient/profile',
    name: 'patientProfile',
    component: PatientProfile,
    meta: { requiresAuth: true, roles: ["ROLE_PATIENT"] },
  },
  {
    path: '/doctor/calendar',
    name: 'doctorCalendar',
    component: DoctorCalendar,
    meta: { requiresAuth: true, roles: ["ROLE_DOCTOR"] },
  },
  {
    path: '/calendar-therapy',
    name: 'therapyCalendar',
    component: TherapyCalendar,
    meta: { requiresAuth: true, roles: ["ROLE_NURSE"] },
  },
  {
    path: '/physiotherapist/calendar',
    name: 'physiotherapistCalendar',
    component: PhysiotherapistCalendar,
    meta: { requiresAuth: true, roles: ["ROLE_PHYSIOTHERAPIST"] },
  },
  {
    path: '/patient/examination-creation',
    name: 'patientExaminationCreation',
    component: PatientExaminationCreationView,
    meta: { requiresAuth: true, roles: ["ROLE_PATIENT"] },
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkActiveClass: "active"
})


router.beforeEach((to, from) => {
  // instead of having to check every route record with
  // to.matched.some(record => record.meta.requiresAuth)
  if (to.meta.requiresAuth && !to.meta.roles.includes(shared.getRoleFromToken())) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    return {
      path: '/login'
    }
  }
})

export default router
