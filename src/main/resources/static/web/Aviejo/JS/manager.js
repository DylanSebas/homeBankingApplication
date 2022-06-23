
// Make a request for a user with a given ID


Vue.createApp({
    data() {
    return {
    clients:[],
    name:"",
    lastName:"",
    email:""
    }
    },
    created() {
        axios.get('http://localhost:8080/api/clients')
    .then(obj=> {
       this.clients=obj.data;

      });
    },
    
    methods: {
      requestPost (){
        axios.post('http://localhost:8080/api/clients', {
          firstName: this.name,
          lastName: this.lastName,
          email: this.email
        })
        
          .then(function (response) {
            console.log(response);
            let url=window.location.href;
            location.reload(url);
          });
        }
            },
    computed: { 

    }

}).mount('#app')