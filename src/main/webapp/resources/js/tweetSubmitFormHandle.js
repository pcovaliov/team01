$("#testForm input[type='submit']").click(function(e){
  e.preventDefault();
  var serializedData =$("#testForm").serializeObject();
  $.ajax({
   contentType: 'application/json',
   type:"POST",
   data:JSON.stringify(serializedData),
   success : function(data, textStatus, jqXHR) {
		tableBodyPoint = $('#tweet-table > tbody');
		tableBodyPoint.lastChild
		tableBody = false;
		// construct the table row
		tr = '';
		tr += '<tr>';
		tr += '<td>';
		tr += 'id';
		tr += '</td>';
		tr += '<td>';
		tr += data['tweet'];
		tr += '</td>';
		tr += '<td>';
		tr += data['date'];
		tr += '</td>';
		tr += '</tr>';
		// end contruct row
		if (tableBodyPoint.children().size() > 0) {
			alert('Consider when there are tweets to insert before');
			tableBodyPoint.append(tr);
		} else {
			tableBodyPoint.append(tr);
		}

	},
	error : function(jqXHR, textStatus, errorThrown) {
		alert("There is an error with tweet publishing");
	}
  });
 });


