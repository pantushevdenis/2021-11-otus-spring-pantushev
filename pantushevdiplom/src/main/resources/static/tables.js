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
    let v_url = '/api/table';

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
                        <td>${enitity.keyTypeFlg}</td>
                        <td>${enitity.objEntityName}</td>
                        <td>
                            <a href="/tableLangs?table_name=${enitity.id}">i18n</a>
                        </td>
                        <td>
                            <a href="/tableFields?table_name=${enitity.id}">Поля</a>
                        </td>
                        <td>
                            <a href="/tables/edit/${enitity.id}">Редактировать</a>
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

function loadObjectData() {
    $.get('/api/table/' + $('#id-input').val()).done(function (object) {
        $('#id-input').val(object.id);
        $('#keyTypeFlg-input').val(object.keyTypeFlg);
        $('#objEntityName-input').val(object.objEntityName);
    })
}

function loadObjectDefault() {

}


function saveObject() {
    let method = ($('#isEdit-input').val() == 'true' ? 'PUT' : 'POST');

    $.ajax(
        '/api/table',
        {
            type: method,
            async: false,
            cache: false,
            data: JSON.stringify( {
                id: $('#id-input').val(),
                keyTypeFlg: $('#keyTypeFlg-input').val(),
                objEntityName: $('#objEntityName-input').val()
            }),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (table) {
                window.location.href = '/tables';
            },
            error: function (jqXHR, textStatus, errorThrown) {
                let message = jqXHR.responseJSON.message;
                $('#errors').empty();
                $('#errors').append(`<p>${message}</p>`);

            }
        }
    )
}






