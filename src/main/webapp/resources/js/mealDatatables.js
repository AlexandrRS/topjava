/**
 * Created by alexandr on 10.11.15.
 */
var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: function (data) {
            updateTableByData(data);
        }
    });
    return false;
}

$(function () {
    datatableApi = $('#datatable').DataTable({
        "sAjaxSource": ajaxUrl,
        "sAjaxDataProp": "",
        "bPaginate": false,
        "bInfo": false,
        "aoColumns": [
            {
                "mData": "dateTime"
            },
            {
                "mData": "description"
            },
            {
                "mData": "calories"
            },
            {
                "bSortable": false,
                "sDefaultContent": "",
                "mRender": renderEditBtn
            },
            {
                "bSortable": false,
                "sDefaultContent": "",
                "mRender": renderDeleteBtn
            }
        ],
        "aaSorting": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            debugger;
            if (data.exceed == true) {
                debugger;
                $(row).addClass('exceeded');
            } else {
                debugger;
                $(row).addClass('normal');
            }
        },
        "initComplete": makeEditable
    });

    $('#filter').submit(function () {
        updateTable();
        return false;
    });

    init();
});

function init() {
}
