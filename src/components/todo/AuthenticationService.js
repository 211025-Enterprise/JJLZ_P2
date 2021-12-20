import axios from 'axios'
class AuthenticationService {

    executeBasicAuthenticationService(response) {
        return axios.get('http://localhost:8080/logged', 
            {headers: {authorization: this.createBasicAuthToken(response)}})
    }

    createBasicAuthToken(response) {  
        return 'Basic ' +  window.btoa(response)
    }

    registerSuccessfulLogin(response){
        //let basicAuthHeader = 'Basic ' +  window.btoa(username + ":" + password)
        //console.log('registerSuccessfulLogin')
        sessionStorage.setItem('authenticatedUser', response)
        this.setupAxiosInterceptors(this.createBasicAuthToken(response))
    }

    logout() {
        sessionStorage.removeItem('authenticatedUser');
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem('authenticatedUser')
        if(user===null) return false
        return true
    }

    getLoggedInUserName = () => {
        let user = sessionStorage.getItem('authenticatedUser')
        if(user===null) return ''
        return user
    }

    setupAxiosInterceptors(basicAuthHeader) {

        axios.interceptors.request.use(
            (config) => {
                if(this.isUserLoggedIn()) {
                    config.headers.authorization = basicAuthHeader
                }
                return config
            }
        )
    }
     // registerSuccessfulLogin(username,password){
    //    // console.log('registerSuccessfulLogin')
    //     sessionStorage.setItem('authenticatedUser', username);
    // }

    // logout() {
    //     sessionStorage.removeItem('authenticatedUser');
    // }

    // isUserLoggedIn() {
    //     let user = sessionStorage.getItem('authenticatedUser')
    //     if(user === null) return false
    //     return true
    // }

}

export default new AuthenticationService()