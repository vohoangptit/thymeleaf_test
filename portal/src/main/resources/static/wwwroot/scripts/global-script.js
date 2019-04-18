; (function () {
    'use strict';

    var gbs = {
        menuToggle: function () {
            $("#menu-toggle").on("click", function (e) {
                e.preventDefault();
                $("#wrapper").toggleClass("toggled");
            });
        },

        showInfoNotify: function (message) {
            var notifyContainer = $('.notify-container');
            if (notifyContainer.length && message.length) {
                notifyContainer.find('.message-content').html(message);
                notifyContainer.find('.alert').addClass('alert-success');
                notifyContainer.find('.alert').removeClass('hide').addClass('show');
                setTimeout(function () {
                    gbs.hideNotify();
                }, 5000);
            }
        },

        showAlertNotify: function (message) {
            var notifyContainer = $('.notify-container');
            if (notifyContainer.length && message.length) {
                notifyContainer.find('.message-content').html(message);
                notifyContainer.find('.alert').addClass('alert-danger');
                notifyContainer.find('.alert').removeClass('hide').addClass('show');
                setTimeout(function () {
                    gbs.hideNotify();
                }, 5000);
            }
        },

        hideNotify: function () {
            var notifyContainer = $('.notify-container');
            if (notifyContainer.length) {
                notifyContainer.find('.message-content').html('');
                notifyContainer.find('.alert').removeClass('show alert-danger alert-success');
                notifyContainer.find('.alert').addClass('hide');
            }
        },

        handlingCloseNotify: function () {
            var self = this;
            $('.close-notify').on('click', function () {
                self.hideNotify();
            });
        },

        showLoading: function () {

        },

        hideLoadding: function () {

        },

        setValidationSettingsDefault: function () {
            $.validator.setDefaults({
                lang: 'ja',
                ignore: ":hidden",
                errorClass: 'invalid-feedback',
                errorElement: 'span',
                errorPlacement: function (error, element) {
                    if (element.closest('div.input-group').length) {
                        $(element).parents('div.input-group-container').append(error);
                        return true;
                    }

                    if (element.closest('div.form-check').length) {
                        error.css('display', 'block');
                        $(element).parents('div.form-check-container').append(error);
                        return true;
                    }

                    error.insertAfter(element);
                },

                showErrors: function (errorMap, errorList) {
                    var i = 0,
                        validators = new Array(this.numberOfInvalids()),
                        rules = this.settings.rules;

                    $.each(errorMap, function (name, value) {
                        validators[i] = {
                            displayName: $("label[for=" + name + "]").text(),
                            name: name
                        };
                        i++;
                    });

                    $.each(errorList, function (index, value) {
                        var validator = validators[index];
                        switch (value.method) {
                            case "rangelength":
                                this.message = gbs.stringFormat(value.message, {
                                    name: validator.displayName,
                                    min: rules[validator.name].rangelength[0],
                                    max: rules[validator.name].rangelength[1]
                                });
                                break;
                            default:
                                this.message = gbs.stringFormat(value.message, { name: validator.displayName });

                                break;
                        }
                    });
                    this.defaultShowErrors();
                },

                highlight: function (element, errorClass) {
                    $(element).addClass('is-invalid');
                },
                unhighlight: function (element, errorClass) {
                    $(element).removeClass('is-invalid');
                }
            });
        },

        setAjaxDefaultSettings: function () {
            // Prepend context path to all jQuery AJAX requests
            $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
                if (!options.crossDomain) {
                    var _ctx = $("meta[name='ctx']").attr("content");
                    options.url = _ctx + options.url;
                }
            });

            $.ajaxSetup({
                cache: false
            });

            gbs.ajax = {};
            $.each(["api"], function (srvIndex, srvName) {
                var srv = {};
                gbs.ajax[srvName] = srv;
                $.each(["get", "put", "post", "delete"], function (index, item) {
                    srv[item] = function (url, data, options) {
                        var settings = {
                            type: item,
                            url: url,
                            data: JSON.stringify(data),
                            contentType: "application/json; charset=utf-8",
                            //dataType: "json",
                        };
                        return $.extend({}, settings, options);
                    };
                    return srv[item];
                });
            });
        },

        announceApiErrorResponse: function (jqXHR) {
            if (jqXHR == null) return;
            var responseJSON = jqXHR.responseJSON;
            if (responseJSON.status != 200) {
                gbs.showAlertNotify(responseJSON.message);
            }
        },

        stringFormat: function (str, col) {
            if (!str.length) return;
            col = typeof col === 'object' ? col : Array.prototype.slice.call(arguments, 1);
            return str.replace(/\{\{|\}\}|\{(\w+)\}/g, function (m, n) {
                if (m == "{{") { return "{"; }
                if (m == "}}") { return "}"; }
                return col[n];
            });
        },

        setErrorMessages: function () {
            gbs.messages = JSON.parse(jsonMessages);
        },

        run: function () {
            this.menuToggle();
            this.setValidationSettingsDefault();
            this.setAjaxDefaultSettings();
            this.handlingCloseNotify();
            this.setErrorMessages();
        }
    }

    $(document).ready(function () {
        gbs.run();
    });

    window.gbs = gbs;
})();