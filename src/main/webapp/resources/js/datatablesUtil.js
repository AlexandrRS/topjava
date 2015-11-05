function makeEditable() {

    $('#add').click(function () {
        $('#id').val(0);
        $('#editRow').modal();
    });

    $('.delete').click(function () {
        var itemId = this.parentNode.parentNode.id;
        debugger;
        deleteRow(itemId);
    });

    $("[name = userIsEnabled]").change(function () {
        var itemId = this.parentNode.parentNode.id;
        var destUrl = ajaxUrl + itemId + '/inverse_is_enabled';
        debugger;
        $.ajax({
            url: destUrl,
            type: 'POST',
            success: function () {
                successNoty('Changed');
            }
        });
    });

    $('#detailsForm').submit(function () {
        save();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('Deleted');
        }
    });
}

function updateTable() {

    var destUrl;
    if (ajaxUrl == 'ajax/meals/') {
        destUrl = ajaxUrl +
            "filter?startDate=" + $("[name = startDate]").val() +
            "&startTime=" + $("[name = startTime]").val()  +
            "&endDate=" + $("[name = endDate]").val() +
            "&endTime=" + $("[name = endTime]").val();
    } else {
        destUrl = ajaxUrl;
    }
    debugger;
    $.get(destUrl, function (data) {
        oTable_datatable.clear();
        $.each(data, function (key, item) {
            oTable_datatable.row.add(item);
        });
        oTable_datatable.draw();
    });
}

function save() {
    var form = $('#detailsForm');
    debugger;
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('Saved');
        }
    });
}

var failedNote;

function closeNote() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNote();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNote();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight'
    });
}
