
Vue.createApp({
    data() {
        return {
            client: [],
            account: [],
            loans: [],
            id: "",
        }
    },
    created() {
        const urlParams = new URLSearchParams(window.location.search);
        const id = urlParams.get('id');
        axios.get(`http://localhost:8080/api/clients/current`)
            .then(obj => {
                this.client = obj.data;
                this.account = this.client.accounts

                console.log(this.client)
                //this.id=obj.data.loan.idLoan;
                console.log(this.account)
                //console.log(this.id)


            });
    },
    methods: {
        exitProfile() {
            axios.post('/api/logout')
            .then(response => console.log('signed out!!!'))
            .then(window.location.href = "http://localhost:8080/web/index1.html")
        },
        createAccount() {
            axios.post('/api/clients/current/accounts')
            .then(response => console.log('successfully created '))
            .then(window.location.href = window.location)
        }
    },
    computed: {

    }

}).mount('#app')