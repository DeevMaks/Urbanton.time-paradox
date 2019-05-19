var app = new Vue({
    el: "#app",
    data: {
        filter_name: "",
        filter_type: "",
        filter_breed: "",
        breeds_selected: [],
        pets: [
            { id: 1, type: "dog", name: "Рекс", breed: "Овчарка", image: "ovcharka.jpg" },
            { id: 2, type: "cat", name: "Мурка", breed: "Нет", image: "cat.jpg" },
            { id: 3, type: "rat", name: "Лариса", breed: "Нет", image: "rat.jpg" },
            { id: 4, type: "dog", name: "Колбаса", breed: "Такса", image: "tax.jpg" },
            { id: 5, type: "cat", name: "Лазанья", breed: "Британская", image: "britain.jpg" },
        ],
        types: [ 
            { name: "dog", title: "Собака" },
            { name: "cat", title: "Кошка" },
            { name: "rat", title: "Крыса" },
        ],
        breeds: [
            { name: "dog", breeds: [ "Овчарка", "Такса" ] },
            { name: "cat", breeds: [ "Британская" ] },
            { name: "rat", breeds: [ ] },
        ]
    },
    methods: {
        filter: function (name, filter) {
            return name.toLowerCase().indexOf(filter.toLowerCase()) != -1;
        },
        sel: function(group) {
            var that = this;
            this.breeds.forEach(function(element){
                console.log( element.name + " and " + that.filter_type );
                if ( element.name == that.filter_type ) {
                    that.breeds_selected = element.breeds;
                }
            })
        }
    }
});