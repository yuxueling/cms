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
    $("#m6").addClass("on");
    $("#m1").removeClass("on");
    vm.listContent();
});


//页中
var vm = new Vue({
    el: '#main',
    data: {
        news:[],
        pageTool:{
            limit:7,
            offset:0,
            total:0,
            currentPage:1,
            currentLength:0,
            totalPages:0,
            goPage:'',
            pageList:[]
        }
    },
    methods: {
        listContent:function () {

            $.ajax({
                url: "/contTpc/listContent",
                type: "post",
                data: {
                    type: 'companyNew',
                    limit:vm.pageTool.limit,
                    offset:vm.pageTool.offset
                },
                success: function (data) {
                    vm.news=data.rows;
                    vm.pageTool.total=data.total;
                    vm.pageTool.currentLength=data.rows.length;
                    vm.pageTool.totalPages=Math.ceil(data.total/vm.pageTool.limit);
                    vm.pageTool.pageList.length = 0;
                    for(var i=1;i<=vm.pageTool.totalPages;i++){
                        if(i> vm.pageTool.currentPage-3 || i< vm.pageTool.currentPage+3){
                            vm.pageTool.pageList.push(i);
                        }
                    }
                }
            });
        },
        selectPage:function (page,step) {
            if(page=='currentPage'){
                page=vm.pageTool.currentPage;
            }
            if(page=='endPage'){
                page=vm.pageTool.totalPages;
            }

            var currentPage=page+step;
            if(page<=1 && step==-1){
                currentPage=1;
            }
            if(page>=vm.pageTool.totalPages && step==1){
                currentPage=vm.pageTool.totalPages;
            }
            vm.pageTool.currentPage = currentPage;
            vm.pageTool.offset = (currentPage-1) * vm.pageTool.limit;
            vm.listContent();
        }
    }
});




