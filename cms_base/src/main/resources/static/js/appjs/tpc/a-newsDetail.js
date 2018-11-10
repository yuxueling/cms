//页头
function searchA() {
    var key = $("#searchKeyA").val();
    window.location.href = "/contTpc/view/a-productCatalog";
}

function searchB() {
    var key = $("#searchKeyB").val();
    window.location.href = "/contTpc/view/a-productCatalog";
}

$(function () {
    $("#m6").addClass("on");
    $("#m1").removeClass("on");
    vm.getContent();
});


//页中
var vm = new Vue({
    el: '#main',
    data: {
        newsCont: {}
    },
    methods: {
        getContent: function () {

            $.ajax({
                url: "/contTpc/getContent",
                type: "post",
                data: {
                    type: 'companyNews'
                },
                success: function (data) {
                    vm.newsCont = data.row;
                }
            });
        }
    }
});




