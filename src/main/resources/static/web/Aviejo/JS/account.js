
// Make a request for a user with a given ID


Vue.createApp({
    data() {
    return {
    client:[],
    account:[],
    transactions:[],
    }
    },
    created() {
        const urlParams= new URLSearchParams(window.location.search);
        const id=urlParams.get('id');
        axios.get(`http://localhost:8080/api/clients/current`)
    .then(obj=> {
       this.client=obj.data;
       this.account=this.client.accounts;
       
       this.transactions= this.account.map(account=>account.transaction)[0];
       console.log(this.transactions)
       

  });
        },
    methods: {
        exitProfile() {
            axios.post('/api/logout')
            .then(response => console.log('signed out!!!'))
            .then(window.location.href = "http://localhost:8080/web/index1.html")
        }
    },
    computed: { 

    }

}).mount('#app')


