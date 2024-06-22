$(document).ready(function() {

    $("#btnAddGenere").click(function() {
        $(".addsBody").append("" +
            "<div class='addBody'>" +
            "<h3><b>Aggiungi Genere</b></h3>" +
            "<button type='button' class='btn-close' aria-label='Close'></button>" +
            "<div class='mb-3'>" +
            "<label class='col-form-label'>Nome :</label>" +
            "<input type='text' class='form-control nomeGenere' name='nomeGenere'>" +
            "</div>" +
            "<div class='mb-3'>" +
            "<label class='col-form-label'>Descrizione:</label>" +
            "<textarea class='form-control descrizione' name='descrizione'></textarea>" +
            "</div></div>"
        );
    });

    $(document).on("click", ".btn-close", function() {
        $(this).parent().remove();
    });

    $(document).on("click", "#btnAddInviaGenere", function() {
        var collectionAddBody = $(".addBody");

        collectionAddBody.each(function() {
            var addBody = $(this);
            var nome = $(this).find(".nomeGenere").val();
            var descrizione = $(this).find(".descrizione").val();
            if (nome != undefined) {
                if (nome != "") {
                    $.ajax({
                        url: "AddGen",
                        type: "post",
                        dataType: 'text',
                        data: "action=add&nome=" + nome + "&descrizione=" + descrizione,
                        success: function(data) {
                            if (data == "") {
                                addBody.css("border-color", "green");
                                console.log(data);
                                // Aggiungi il messaggio di successo
                                addBody.append("<p class='success-message'>Genere aggiunto correttamente!</p>");
                            } else {
                                addBody.empty();
                                addBody.css("border-color", "red");
                                addBody.append("<button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Close'></button>");
                                addBody.append("<h3>" + data + "</h3>");
                            }
                        },
                        error: function(textStatus, errorThrown) {
                            console.log(textStatus, errorThrown);
                        }
                    });
                } else {
                    $(this).css("border-color", "red");
                }
            } else {
                addBody.remove();
            }
        });
    });
});