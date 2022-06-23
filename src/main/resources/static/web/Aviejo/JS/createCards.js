// Make a request for a user with a given ID


Vue.createApp({
    data() {
        return {
            client: [],
            cards: [],
            name: "",
            type:"",
            cardColorType:""
        }
    },
    created() {
        const urlParams = new URLSearchParams(window.location.search);
        const id = urlParams.get('id');
        axios.get(`http://localhost:8080/api/clients/current`)
            .then(obj => {
                this.client = obj.data;
                this.cards = this.client.cards;
                console.log(this.client);
                console.log(this.cards);
                console.log(this.type)

            });
    },
    methods: {
        dataPost() {
            axios.post('/api/clients/current/cards', `cardType=${this.type}&cardColorType=${this.cardColorType}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } }).then(response => console.log('signed in!!!'))
                .then(function (response) {
                    console.log(response);
                    let url = window.location.href;
                    window.location.href = "http://localhost:8080/web/cards1.html";
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        exitProfile() {
            axios.post('/api/logout')
            .then(response => console.log('signed out!!!'))
            .then(window.location.href = "http://localhost:8080/web/index1.html")
        }
    },
    computed: {

    }

}).mount('#app')

