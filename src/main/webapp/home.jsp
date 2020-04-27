<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="common/css/bootstrap.min.css">
  <!-- Material Design for Bootstrap CSS -->
  <link rel="stylesheet"
    href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css"
    integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX" crossorigin="anonymous">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
    integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
  <link rel="stylesheet" href="common/css/style.css">
  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
  <link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">
 
  <script>
  /**
   * 
   */
   
  

  $(document)
  		.ready(
  				function() {
  					// called when key is pressed in textbox
  					$(
  							"#society_add_mobile , #society_edit_mobile ,#customer_add_CRN , customer_edit_CRN , #customer_add_mobile, #customer_edit_mobile, #customer_add_meterNo , #customer_add_AMR")
  							.keypress(
  									function(e) {
  										// if the letter is not digit then
  										// display error and don't type anything
  										if (e.which != 8
  												&& e.which != 0
  												&& (e.which < 48 || e.which > 57)) {
  											// display error message
  											Materialize.toast('Select Digit!',
  													40);
  											return false;
  										}
  									});

  					$(
  							"#society_add_name , #society_edit_name, #customer_add_firstName, #customer_add_lastName ,#customer_edit_firstName, #customer_edit_lastName")
  							.keypress(
  									function(e) {
  										// if the letter is not digit then
  										// display error and don't type anything
  										var regex = new RegExp("^[a-zA-Z ]*$");
  										var str = String
  												.fromCharCode(!e.charCode ? e.which
  														: e.charCode);
  										if (regex.test(str)) {
  											return true;
  										} else {
  											e.preventDefault();
  											Materialize.toast(
  													'Select Alphabet!', 40);
  											return false;
  										}
  									});

  				});
  </script>
  
  <title>Home</title>
</head>

<body>
<jsp:include page="header.jsp" />
  <div class="container-fluid topspacing bottomspacing pl-0 pr-0 mr-0 ml-0">
    <div class="row mr-0 ml-0">
      <div class="col-md-2 pl-0 pr-0">
       <jsp:include page="menu.jsp" />
      </div>
      
    </div>
  </div>

  <jsp:include page="footer.jsp" />
  <!-- Modal -->
  
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
    integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
    crossorigin="anonymous"></script>
  <script src="common/js/bootstrap.min.js"></script>
  <script src="js/validations.js"></script>
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://unpkg.com/popper.js@1.12.6/dist/umd/popper.js"
    integrity="sha384-fA23ZRQ3G/J53mElWqVJEGJzU0sTs+SvzG8fXVWP+kJQ1lwFAOkcUOysnlKJC33U"
    crossorigin="anonymous"></script>
  <script src="https://unpkg.com/bootstrap-material-design@4.1.1/dist/js/bootstrap-material-design.js"
    integrity="sha384-CauSuKpEqAFajSpkdjv3z9t8E7RlpJ1UP0lKM/+NdtSarroVKu069AlsRPKkFBz9"
    crossorigin="anonymous"></script>
  <script>$(document).ready(function () { $('body').bootstrapMaterialDesign(); });</script>
  <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
  <script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
  <script src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>
  
  <script>
    $(document).ready(function () {
    	var flag =true;
      $('.button-left').click(function () {
        $('.left ').toggleClass('fliph');
      });

    });
  </script>
  <script>
    $(document).ready(function () {
      $('#example').DataTable();
    });
  </script>
  
</body>

</html>