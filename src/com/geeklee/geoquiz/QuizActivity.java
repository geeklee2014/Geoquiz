package com.geeklee.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity
{
	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
	private TextView mQuestionTextView;

	private TrueFalse[] mQuestionBank = new TrueFalse[] {
			new TrueFalse(R.string.question_oceans, true),
			new TrueFalse(R.string.question_mideast, false),
			new TrueFalse(R.string.question_africa, false),
			new TrueFalse(R.string.question_americas, true),
			new TrueFalse(R.string.question_asia, true),
	};
	private int mCurrentIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_quiz);
		
		mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
		updateQuestion();
		
		mTrueButton = (Button) findViewById(R.id.true_button);
		mFalseButton = (Button) findViewById(R.id.false_button);
		mNextButton = (Button) findViewById(R.id.next_button);

		mTrueButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				checkAnswer(true);
			}
		});
		mFalseButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				checkAnswer(false);
			}
		});
		mNextButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();
			}
		});
	}

	private void updateQuestion()
	{
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);		
	}
	private void checkAnswer(boolean userpressedTrue)
	{
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		
		int messageResId = 0;
		
		if (userpressedTrue == answerIsTrue)
			{
				messageResId = R.string.correct_toast;
			}
		else
			{
				messageResId = R.string.incorrect_toast;
			}
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}
}
