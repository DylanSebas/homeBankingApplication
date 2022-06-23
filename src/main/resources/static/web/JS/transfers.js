
Vue.createApp({
    data() {
        return {
            client: [],
            accounts: [],
            send: "",
            amount: "",
            description: "",
            sourceNumber: "",
            numberToDestination: "",
            transaction: [],
            filter: [],

        }
    },
    created() {
        axios.get(`/api/clients/current`)
            .then(obj => {
                this.client = obj.data;
                this.accounts = this.client.accounts
                console.log(this.client)
                console.log(this.accounts)


            });
    },
    methods: {

        exitProfile() {
            axios.post('/api/logout')
                .then(response => console.log('signed out!!!'))
                .then(window.location.href = "/web/index1.html")
        },
        transfer() {
            console.log(this.sourceNumber)
            this.transaction = this.accounts.filter(account => account.number != this.sourceNumber.number)


        },
        dataPostRegister() {

        },
        sendTransfer() {
            console.log(this.sourceNumber.number)
            console.log(this.numberToDestination.number)
            Swal.fire({
                title: `are you sure you want to make the transfer`,
                icon: `info`,
                backdrop: true,
                showCancelButton: true,
                confirmButtonText: `Yes`,
                ShowConfirmButton: true,
                confirmButtonColor: '#009ee3',
                confirmButtonArialLabel: 'confirm',
                showCloseButton: true,
                allowOutsideClick: false,
                allowEscapeKey: false,
            }).then(result => {
                if (result.isConfirmed) {
                    axios.post('/api/transactions', `description=${this.description}&sourceNumber=${this.sourceNumber.number}&numberToDestination=${this.numberToDestination.number}&amount=${this.amount}`) 
                        .then(result => {
                            swal.fire({
                                icon: `success`,
                                text: `transfer done`
                            })
                        })
                        
        }
    }).catch(function (error) {
        console.log(error);
    })
        },
    },
computed: {

}

}).mount('#app')