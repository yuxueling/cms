//页头
function searchA() {
    var key=$("#searchKeyA").val();
    window.location.href = "/contTpc/view/a-productCatalog" ;
}
function searchB() {
    var key=$("#searchKeyB").val();
    window.location.href = "/contTpc/view/a-productCatalog" ;
}

$(function() {
    $("#m4").addClass("on");
    $("#m1").removeClass("on");
    vm.listContent();
});


//页中
var vm = new Vue({
    el: '#main',
    data: {
        aboutUsContList:[]
    },
    methods: {
        listContent:function () {

            $.ajax({
                url: "/contTpc/listContent",
                type: "post",
                data: {
                    type: 'companyProfile',
                    limit:3,
                    offset:0
                },
                success: function (data) {
                    vm.aboutUsContList=data.rows;
                }
            });
        }
    }
});



