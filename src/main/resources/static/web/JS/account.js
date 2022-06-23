
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

        axios.get(`/api/clients/current`)
    .then(obj=> {
       this.client=obj.data;
       
       console.log(this.client);
       this.account=this.client.accounts;
       
      // this.transactions= this.account.map(account=>account.transaction)[0];
      
         
       

  });
        },
    methods: {
        exitProfile() {
            axios.post('/api/logout')
            .then(response => console.log('signed out!!!'))
            .then(window.location.href = "/web/index1.html")
        },
        
    },
    computed: { 

    }

}).mount('#app')


