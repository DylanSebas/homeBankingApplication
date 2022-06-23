// Make a request for a user with a given ID


Vue.createApp({
    data() {
        return {
            client: [],
            cards: [],
            name: "",
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

            });
    },
    methods: {
        girarTarjeta(id) {

            const tarjeta = document.querySelector(`#card-${id}`);
            tarjeta.classList.toggle('active');

        },

        dateFormat(day) {
            const fecha = new Date(day);
          return fecha.getMonth() + "/" + fecha.getFullYear();
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

