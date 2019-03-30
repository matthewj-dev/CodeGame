package com.lapis.codefun

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SubMenu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.view.menu.SubMenuBuilder
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_game_page.*

class GamePage : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)

        var testQuestion = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation"
        var answers = arrayListOf(
            Pair("while i < 3:", arrayListOf("while i == 3", "while i < 3:", "while i >= 3:")),
            Pair("\tprint(\"Hello World\")", arrayListOf("print(Hello World)", "print \"Hello World\"")),
            Pair("\ti += 1", arrayListOf("i - 1", "i + 2", "i == 1"))
        )

        questionField.text = testQuestion
        questionRecycler.adapter = AnswersAdapter(this, answers)
        questionRecycler.layoutManager = GridLayoutManager(this, 1)

    }
}

class AnswersAdapter(private val context: Context, private val answers: ArrayList<Pair<String, ArrayList<String>>>): RecyclerView.Adapter<AnswerCell>()
{
    override fun getItemCount(): Int = answers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerCell
    {
        return AnswerCell(View.inflate(context, R.layout.answer_view, null))
    }

    override fun onBindViewHolder(answerCell: AnswerCell, position: Int)
    {
        answerCell.setText(answers[position].first)
        answerCell.setAnswers(answers[position].second)
    }
}

class AnswerCell(view: View): RecyclerView.ViewHolder(view)
{
    private var answerTextView: TextView? = null
    private var answers = arrayListOf<String>()

    init
    {
        answerTextView = view.findViewById(R.id.answerTextView)
        view.setOnClickListener {
            if (answers.isEmpty()) return@setOnClickListener

            val popupMenu = PopupMenu(view.context, view)
            answers.forEach { popupMenu.menu.add(it) }

            popupMenu.show()

            popupMenu.setOnMenuItemClickListener {
                //TODO: Hook into API for game when clicked
                return@setOnMenuItemClickListener true
            }
        }
    }

    fun setText(text: String)
    {
        answerTextView?.text = text
    }

    fun setAnswers(answers: ArrayList<String>)
    {
        this.answers.clear()
        this.answers.addAll(answers)
    }
}
