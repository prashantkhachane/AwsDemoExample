
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8" />
<title>Employee Record</title>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
<script
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Edit Employee</h4>
					</div>
					<div class="modal-body">
						<div align="center">
							<div class="row">
								<div class="col-md-4">
									<label>Name:</label>
								</div>
								<div class="col-md-8">
									<input type="hidden" name="id" id="id">
									<input type="text" name="name" id="name">
								</div>

							</div>
							<div class="row" style="margin-top: 2%;">
								<div class="col-md-4">
									<label>Email</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="email" id="email">
								</div>
							</div>
							<div class="row" style="margin-top: 2%;">
								<div class="col-md-4">
									<label>Phone No</label>
								</div>
								<div class="col-md-8">
								<input type="text" name="phoneNo" id="phoneNo">
								</div>
							</div>
							<div class="row" style="margin-top: 2%;">
								<div class="col-md-4">
									<label>City</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="city" id="city">
								</div>
							</div>
							<div class="row" style="margin-top: 2%;">
								<div class="col-md-4">
									<label>State</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="state" id="state">
								</div>
							</div>
							<div class="row" style="margin-top: 2%;">
								<div class="col-md-4">
									<label>Country</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="country" id="country">
								</div>
							</div>

						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-default" data-dismiss="modal" id="update"
							name="update">Update</button>

					</div>
				</div>

			</div>
		</div>

	</div>

	<h1>Employee Table</h1>


	<table id="empTable" class="display">

		<!-- Header Table -->
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Phone No</th>
				<th>City</th>
				<th>State</th>
				<th>Country</th>
				<th>Edit</th>
				<th>Pdf</th>

			</tr>
		</thead>
	</table>

</body>
<script type="text/javascript">
	//Plug-in to fetch page data 
	jQuery.fn.dataTableExt.oApi.fnPagingInfo = function(oSettings) {
		return {
			"iStart" : oSettings._iDisplayStart,
			"iEnd" : oSettings.fnDisplayEnd(),
			"iLength" : oSettings._iDisplayLength,
			"iTotal" : oSettings.fnRecordsTotal(),
			"iFilteredTotal" : oSettings.fnRecordsDisplay(),
			"iPage" : oSettings._iDisplayLength === -1 ? 0 : Math
					.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength),
			"iTotalPages" : oSettings._iDisplayLength === -1 ? 0 : Math
					.ceil(oSettings.fnRecordsDisplay()
							/ oSettings._iDisplayLength)
		};
	};
	$(document).ready(function() {
		var table=$("#empTable").dataTable( {
	        "bProcessing": true,
	        "bServerSide": true,
	        "sort": "position",
	        //bStateSave variable you can use to save state on client cookies: set value "true" 
	        "bStateSave": false,
	        //Default: Page display length
	        "iDisplayLength": 10,
	        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
	        "iDisplayStart": 0,
	        "fnDrawCallback": function () {
	            //Get page numer on client. Please note: number start from 0 So
	            //for the first page you will see 0 second page 1 third page 2...
	            //Un-comment below alert to see page number
	        	//alert("Current page number: "+this.fnPagingInfo().iPage);    
	        },         
	        "sAjaxSource": "getAllEmployee",
	        "aoColumns": [
	            { "mData": "id" },
	            { "mData": "name" },
	            { "mData": "email" },
	            { "mData": "phoneNo" },
	            { "mData": "city" },
	            { "mData": "state" },
	            { "mData": "country" },
	            {
					'mData' : null,
					'render' : function(
							mData, type,
							row) {
						return '<button id="'
								+ row.id
								+ '" onClick=editClick(this.id); name=edit>Edit</button>'
					}
				},
				{
					'mData' : null,
					'render' : function(
							mData, type,
							row) {
//  						return '<button id="'
//  								+ row.id
//  								+ '" onClick=downloadPdf(this.id); name=PDF>PDF</button>'

// 							return '<form:form action="download" method="post" id="downloadPdf"><input type="hidden" name="'+id+'" value="'+this.id+'"><input id="submitId" type="submit" value="Download pdf"></form:form>'
					return "<form action='download' method='Post'><input type='hidden' name='id' Value='"+row.id+"'><input type='submit' value='PDF'></form>"	
						
					}
				},
	             
	        ]
	    } );

					});
 $("#update").click(function() {
	 var id=$("#id").val();
	 var name=$("#name").val();
	 var email=$("#email").val();
	 var phoneNo=$("#phoneNo").val();
	 var city=$("#city").val();
	 var state=$("#state").val();
	 var country=$("#country").val();
	 $.ajax({
			url :  "/update",
			type : "Put",
			data : {
				id:id,
				name:name,
				email:email,
				phoneNo:phoneNo,
				city:city,
				state:state,
				country:country
				}	
			}).done(function(result){
				if (result=="SUCCESS") {
					$('#empTable').DataTable({
						"bProcessing": true,
				        "bServerSide": true,
				        "sort": "position",
				        "destroy": true,
				        //bStateSave variable you can use to save state on client cookies: set value "true" 
				        "bStateSave": false,
				        //Default: Page display length
				        "iDisplayLength": 10,
				        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
				        "iDisplayStart": 0,
				        "fnDrawCallback": function () {
				            //Get page numer on client. Please note: number start from 0 So
				            //for the first page you will see 0 second page 1 third page 2...
				            //Un-comment below alert to see page number
				        	//alert("Current page number: "+this.fnPagingInfo().iPage);    
				        }, 
				        "sAjaxSource": "getAllEmployee",
				        "aoColumns": [
				            { "mData": "id" },
				            { "mData": "name" },
				            { "mData": "email" },
				            { "mData": "phoneNo" },
				            { "mData": "city" },
				            { "mData": "state" },
				            { "mData": "country" },
				            {
								'mData' : null,
								'render' : function(
										mData, type,
										row) {
									return '<button id="'
											+ row.id
											+ '" onClick=editClick(this.id); name=edit>Edit</button>'
								}
							},
							{
								'mData' : null,
								'render' : function(
										mData, type,
										row) {
									return "<form action='download' method='Post'><input type='hidden' name='id' Value='"+row.id+"'><input type='submit' value='download'></form>"	
									
// 									return '<button id="' + row.id + '" onClick=downloadPdf(this.id); name=PDF>PDF</button>'
// 											return "<form action='download' method='Post'><input type='hidden' name='id' Value='"+this.id+"'><input type='submit' value='download'></form>"
								}
							},
				             
				        ]
				})
				} else if (responseText == "FAIL"){
					$('#result').html("<font color='red'>Registration Failed...!!!</font>");
				}
		
		
		});

	}); 
	function editClick(id) {
		$.ajax({
			url : "/edit?id=" + "'" + id + "'",
			type : "Put"
		}).done(function(data) {
			$("#myModal").modal('show');
			$("#id").val(data.id);
			$("#name").val(data.name);
			$("#email").val(data.email);
			$("#phoneNo").val(data.phoneNo);
			$("#city").val(data.city);
			$("#state").val(data.state);
			$("#country").val(data.country);
		});

	}
	function downloadPdf(id) {
		alert(id);
		$.ajax({
			url : "/download?id=" + "'" + id + "'",
			type : "Post"
		}).done(function(data) {
			
		});

	}
</script>
</html>

