Vue.createApp({
    data() {
        return {
            client: [],
            loans: [],
            accounts: [],
            payments: "",
            amount: "",
            idLoan: "",
            destinationAccount: "",
            interest: "",

            filter: [],
        }
    },
    created() {
        axios.get(`/api/clients/current`)
            .then(obj => {
                this.client = obj.data;
                this.accounts = this.client.accounts
                console.log(this.accounts)
            });

        axios.get(`/api/loans`)
            .then(obj => {
                this.loans = obj.data;
                console.log(this.loans)
            });
    },
    methods: {
        payment() {
            console.log(this.idLoan)
            this.filter = this.loans[--this.idLoan]
            console.log(this.filter)
        },

        exitProfile() {
            axios.post('/api/logout')
                .then(response => console.log('signed out!!!'))
                .then(window.location.href = "/web/index1.html")
        },

        sendLoan() {
            this.interest = (this.amount + (20 / 100.0) * this.amount) / this.payments;
            let loan = {
                "payments": this.payments,
                "amount": this.amount,
                "idLoan": ++this.idLoan,
                "destinationAccount": this.destinationAccount.number,
            }
            Swal.fire({
                title: `your loan is from:${this.amount} the monthly fee is $ ${this.interest}`,
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
                    axios.post('/api/loans', loan)
                        .then(result => {
                            swal.fire({
                                icon: `success`,
                                text: `loan adquired`
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