export default {
    getRoleFromToken() {
        const jwtToken = window.sessionStorage.getItem('jwt')
        if (jwtToken) {
          const tokenSplit = jwtToken.split('.')
          const decoded = decodeURIComponent(encodeURIComponent(window.atob(tokenSplit[1])))
          const obj = JSON.parse(decoded)
          const expDate = new Date(obj.exp * 1000)
          if(expDate > new Date()) return obj.role
        }
        return ""
      }
  }
  