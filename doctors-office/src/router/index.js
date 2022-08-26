import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from '../views/Login.vue'
import shared from '../shared'
import AppointmentCalendar from '../views/AppointmentCalendar.vue'
import PatientCalendar from '../views/PatientCalendar.vue'
import PatientsProfiles from '../views/PatientsProfiles.vue'
import PatientProfile from '../views/PatientProfile.vue'
import DoctorCalendar from '../views/DoctorCalendar.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    meta: { requiresAuth: true, role: "ROLE_DOCTOR" },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  { path: '/login', name: 'Login', component: Login },
  {
    path: '/calendar',
    name: 'appointmentCalendar',
    component: AppointmentCalendar,
    meta: { requiresAuth: true, role: "ROLE_NURSE" },
  },
  {
    path: '/patient/calendar',
    name: 'patientCalendar',
    component: PatientCalendar,
    meta: { requiresAuth: true, role: "ROLE_PATIENT" },
  },
  {
    path: '/patient-profiles',
    name: 'PatientsProfiles',
    component: PatientsProfiles,
    meta: { requiresAuth: true, role: "ROLE_NURSE" },
  },
  {
    path: '/patient/profile',
    name: 'patientProfile',
    component: PatientProfile,
    meta: { requiresAuth: true, role: "ROLE_PATIENT" },
  },
  {
    path: '/doctor/calendar',
    name: 'doctorCalendar',
    component: DoctorCalendar,
    meta: { requiresAuth: true, role: "ROLE_DOCTOR" },
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


router.beforeEach((to, from) => {
  // instead of having to check every route record with
  // to.matched.some(record => record.meta.requiresAuth)
  if (to.meta.requiresAuth && shared.getRoleFromToken()!==to.meta.role) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    return {
      path: '/login'
    }
  }
})

export default router
