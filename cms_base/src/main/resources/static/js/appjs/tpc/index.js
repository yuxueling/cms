//页头
function searchA() {
    var key=$("#searchKeyA").val();
    window.location.href = "/contTpc/view/a-productCatalog";
}
function searchB() {
    var key=$("#searchKeyB").val();
    window.location.href = "/contTpc/view/a-productCatalog";
}

function viewProduct(contProductId) {
    window.location.href="/contTpc/showProduct/"+contProductId;
}


$(function() {
    $("#m1").addClass("on");

});




