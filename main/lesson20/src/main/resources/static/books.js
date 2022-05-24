function loadBooksList() {
    $.get('/api/books').done(function (books) {
        $('#books-list > tbody').empty();

        books.forEach(function (book) {
            $('#books-list > tbody').append(`
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.name}</td>
                        <td>${book.author.fullName}</td>
                        <td>${book.jenre.fullName}</td>
                        <td>${book.publishingYear}</td>
                        <td>
                            <button onclick="document.location='/books/edit/${book.id}'">Редактировать</button>
                        </td>
                        <td>
                            <button onclick="deleteBook('${book.id}')">Удалить</button>
                        </td>
                    </tr>
                `)
        });
    })
}


function loadAuthorsList() {
    $('#authors-list > tbody').append('<tr>' +
        '<td>1</td>' +
        '<td>Полное имя автора</td>' +
        '<td>Краткое имя автора</td>' +
        '<td>Дата рождения</td>' +
        '</tr>')

    $.get('/api/authors').done(function (authors) {
        $('#authors-list > tbody').empty();

        authors.forEach(function (author) {
            $('#authors-list > tbody').append(`
                    <tr>
                        <td>${author.id}</td>
                        <td>${author.fullName}</td>
                        <td>${author.shortName}</td>
                        <td>${author.dateOfBirdth}</td>
                    </tr>
                `)
        });
    })
}

function loadJenresList() {
    $('#jenres-list > tbody').append('<tr>' +
        '<td>1</td>' +
        '<td>Какое-то полное наименование жанра</td>' +
        '<td>Какое-то краткое наименование жанра</td>' +
        '</tr>')

    $.get('/api/jenres').done(function (jenres) {
        $('#jenres-list > tbody').empty();

        jenres.forEach(function (jenre) {
            $('#jenres-list > tbody').append(`
                    <tr>
                        <td>${jenre.id}</td>
                        <td>${jenre.fullName}</td>
                        <td>${jenre.shortName}</td>                        
                    </tr>
                `)
        });
    })
}

function loadDropdownAuthors() {
    if ($('#isEdit-input').val() == "false") {
        $('#author-input').append('<option selected="selected" value="">---</option>')
    }
    $('#author-input').append('<option value="1">Какой-то автор 1</option>')

    $.get('/api/dropdowns/authors').done(function (authors) {
        $('#author-input').empty();
        if ($('#isEdit-input').val() == "false") {
            $('#author-input').append('<option selected="selected" value="">---</option>')
        }

        authors.forEach(function (author) {
            $('#author-input').append(`
                        <option value="${author.id}">${author.shortName}</option>
                `)
        });
    })
}


function loadDropdownJenres() {
    if ($('#isEdit-input').val() == "false") {
        $('#jenre-input').append('<option selected="selected" value="">---</option>')
    }
    $('#jenre-input').append('<option value="1">Какой-то жанр 1</option>')

    $.get('/api/dropdowns/jenres').done(function (jenres) {
        $('#jenre-input').empty();
        if ($('#isEdit-input').val() == "false") {
            $('#jenre-input').append('<option selected="selected" value="">---</option>')
        }

        jenres.forEach(function (jenre) {
            $('#jenre-input').append(`
                        <option value="${jenre.id}">${jenre.shortName}</option>
                `)
        });
    })
}

function loadBookData() {
    $.get('/api/book/' + $('#id-input').val()).done(function (book) {
        $('#name-input').val(book.name);
        $('#author-input').val(book.author.id);
        $('#jenre-input').val(book.jenre.id);
        $('#publishingYear-input').val(book.publishingYear);
    })
}

function loadBookDefault() {

}

function loadBookEdit() {
    loadDropdownAuthors();
    loadDropdownJenres();
    if ($('#isEdit-input').val() == 'true') {
        loadBookData();
    }
    else {
        loadBookDefault();
    }
}

function saveBook() {
    let method = ($('#isEdit-input').val() == 'true' ? 'PUT' : 'POST');

    $.ajax(
        '/api/book',
        {
            type: method,
            async: false,
            cache: false,
            data: JSON.stringify( {
                id: $('#id-input').val(),
                name: $('#name-input').val(),
                authorId: $('#author-input').val(),
                jenreId: $('#jenre-input').val(),
                publishingYear: $('#publishingYear-input').val()
            }),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function () {
                window.location.href = '/books';
            },
            error: function (jqXHR) {
                let message = jqXHR.responseJSON.message;
                $('#errors').empty();
                $('#errors').append(`<p>${message}</p>`);

            }
        }
    )
}

function deleteBook(id) {
    if (!confirm('Are you sure you want to delete this book?')) {
        return;
    }

    $.ajax({
        url: '/api/book/' + id,
        type: 'DELETE',
        success: function () {
            loadBooksList();
        }
    });
}


