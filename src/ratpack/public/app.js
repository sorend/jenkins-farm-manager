

var app = new Vue({
    el: "#app",
    mounted: function() {
        var self = this;
        $(document).ready(function() {
            $.ajax({
                url: '/stacks',
                method: 'GET',
            }).done(function(data) {
                self.stacks = data;
            }).fail(function(error) {
                console.log(error);
            });
        });
    },
    data: {
        stacks: [],
        message: "Started"
    },
});