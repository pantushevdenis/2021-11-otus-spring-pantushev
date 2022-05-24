function reloadTableList() {
    let page = $('#input-page').val();
    let size = $('#input-size').val();
    loadTablesList(page, size, false, false);
}

function loadTableListPrev() {
    let page = $('#input-page').val();
    let size = $('#input-size').val();
    loadTablesList(page, size, true, false);
}

function loadTableListNext() {
    let page = $('#input-page').val();
    let size = $('#input-size').val();
    loadTablesList(page, size, false, true);
}

function loadTablesList(page, size, prev, next) {
    let v_url = '/api/field';
    let firstParam = true;
    if (page != 0) {
        if (firstParam) {
            v_url += '?';
            firstParam = false;
        }
        else {
            v_url += '&';
        }
        v_url += 'page=' + page;
    }
    if (prev) {
        if (firstParam) {
            v_url += '?';
            firstParam = false;
        }
        else {
            v_url += '&';
        }
        v_url += 'prev=true';
    }
    else if (next) {
        if (firstParam) {
            v_url += '?';
            firstParam = false;
        }
        else {
            v_url += '&';
        }
        v_url += 'next=true';
    }
    if (size != 0) {
        if (firstParam) {
            v_url += '?';
        }
        else {
            v_url += '&';
        }
        v_url += 'size=' + size;
    }
    $.get(v_url).done(function (entities) {
        $('#tables-list > tbody').empty();

        entities.content.forEach(function (enitity) {
            $('#tables-list > tbody').append(`
                    <tr>
                        <td>${enitity.id}</td>
                        <td>${enitity.dataTypeFlg}</td>
                        <td>${enitity.signedSw}</td>
                        <td>${enitity.fldPrecision}</td>
                        <td>${enitity.fldScale}</td>
                        <td>${enitity.objPropertyName}</td>
                        <td>
                            <a href="/fieldLangs?field_name=${enitity.id}">i18n</a>
                        </td>
                        <td>
                            <a href="/fields/edit/${enitity.id}">Редактировать</a>
                        </td>
                        <td>
                            <a href="#" onclick="deleteTable(${enitity.id})">Удалить</a>
                        </td>
                    </tr>
                `)
        });
        $('#input-page').val(entities.number);
        $('#input-size').val(entities.size);
        $('#button-prev').attr('disabled', entities.first);
        $('#button-next').attr('disabled', entities.last);
        $('#text-totalPages').text(entities.totalPages);
    })
}

function loadObjectEdit() {
    //loadDropdownAuthors();
    //loadDropdownJenres();
    if ($('#isEdit-input').val() == 'true') {
        loadObjectData();
        $('#id-input').attr('readonly', 'readonly');
    }
    else {
        loadObjectDefault();
    }
}

function loadObjectDefault() {

}



function loadObjectData() {
    $.get('/api/field/' + $('#id-input').val()).done(function (object) {
        $('#id-input').val(object.id);
        $('#dataTypeFlg-input').val(object.dataTypeFlg);
        $('#signedSw-input')[0].checked = object.signedSw;
        $('#fldPrecision-input').val(object.fldPrecision);
        $('#fldScale-input').val(object.fldScale);
        $('#objPropertyName-input').val(object.objPropertyName);
    })
}



function saveObject() {
    let method = ($('#isEdit-input').val() == 'true' ? 'PUT' : 'POST');

    $.ajax(
        '/api/field',
        {
            type: method,
            async: false,
            cache: false,
            data: JSON.stringify( {
                id: $('#id-input').val(),
                dataTypeFlg: $('#dataTypeFlg-input').val(),
                signedSw: $('#signedSw-input')[0].checked,
                fldPrecision: $('#fldPrecision-input').val(),
                fldScale: $('#fldScale-input').val(),
                objPropertyName: $('#objPropertyName-input').val()
            }),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (field) {
                window.location.href = '/fields';
            },
            error: function (jqXHR, textStatus, errorThrown) {
                let message = jqXHR.responseJSON.message;
                $('#errors').empty();
                $('#errors').append(`<p>${message}</p>`);

            }
        }
    )
}






