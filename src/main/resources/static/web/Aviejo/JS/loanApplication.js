Vue.createApp({
    data() {
        return {
            client: [],
            loans: [],
            payments: "",
            amount: "",
            idLoan: "",
            destinationAccount: "",
            loan: {
                "payments": this.payments,
                "amount": this.amount,
                "idLoan": this.idLoan,
                "destinationAccount": this.destinationAccount,
            },
            filter: [],
            interest: ""
        }
    },
    created() {
        axios.get(`http://localhost:8080/api/clients/current`)
            .then(obj => {
                this.client = obj.data;
            });

        axios.get(`http://localhost:8080/api/loans`)
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
                .then(window.location.href = "http://localhost:8080/web/index1.html")
        },
        interests() {
            this.interest = (this.amount + (20 / 100.0) * this.amount) / this.payments
            return this.interest;
        },

        sendLoan() {
            Swal.fire({
                title: `your loan is from:${this.amount}`,
                icon: `info`,
                text: `are you sure to get this loan?`,
                html: `<p>the monthly fee is {{interes}} </p>`,
                backdrop: true,
                confirmButtonText: `Yes`,
                ShowConfirmButton: true,
                confirmButtonColor: '#5995fd',
                confirmButtonArialLabel:'confirm',
                showCloseButton:true,
                allowOutsideClick:false,
                allowEscapeKey:false,
                
            });
        },
        dataPost() {
            axios.post('/api/loans', `${this.loan}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } }).then(response => console.log('created loan!!!'))
                .then(function (response) {
                    console.log(response);
                    window.location.href = "http://localhost:8080/web/loanApplication.html";
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
    },
    computed: {

    }

}).mount('#app')