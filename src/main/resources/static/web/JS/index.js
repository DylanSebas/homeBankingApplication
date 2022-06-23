Vue.createApp({
    data() {
        return {
            client: [],
            cards: [],
            name: "",
            email: "",
            password: "",
            login: "logins",
            firstName: "",
            lastName: "",
            passwordRegister: "",
            emailRegister: "",
        }
    },
    created() {

    },
    methods: {
        dataPost() {
            axios.post('/api/login', `email=${this.email}&password=${this.password}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } }).then(response => console.log('signed in!!!'))
                .then(function (response) {
                    console.log(response);
                    let url = window.location.href;
                    window.location.href = "/web/account1.html";
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        
        dataPostRegister() {
            axios.post('/api/clients', `firstName=${this.firstName}&lastName=${this.lastName}&email=${this.emailRegister}&password=${this.passwordRegister}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } }).then(response => console.log('registered'))
                .then(() => {
                    axios.post('/api/login', `email=${this.emailRegister}&password=${this.passwordRegister}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                        .then(response => {

                            let url = window.location.href
                            window.location.href = "/web/account1.html"
                        })
                });
        }

    },
    computed: {

    }

}).mount('#app')
const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener("click", () => {
  container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
  container.classList.remove("sign-up-mode");
});