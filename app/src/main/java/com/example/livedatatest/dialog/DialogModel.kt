package com.example.livedatatest.dialog

/**
 * AlertDialog를 구성하는 파일
 */
// AlertDialog에 표시될 제목, 내용, 확인 버튼
data class DialogModel(
    var title: String? = null,
    var message: String? = null,
    var positiveButton: DialogButton? = null
)

// AlertDialog 확인 버튼 속성
data class DialogButton(
    var text: String? = null,
    var task: (() -> Unit)? = null
)

object DialogModelBuilder {
    private var dialogModel = DialogModel()

    fun title(title: String) {
        dialogModel.title = title
    }

    fun message(message: String) {
        dialogModel.message = message
    }

    fun button(text: String, task: (() -> Unit)? = null) {
        dialogModel.positiveButton = DialogButton(text, task)
    }

    fun build() = dialogModel
}

fun dialog(builder: DialogModelBuilder.() -> Unit): DialogModel =
    DialogModelBuilder.apply(builder).build()