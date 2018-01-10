package com.example.crack.tabbedactivitykotlin

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainActivity : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter


        // Float button을 클릭했을 때!!!
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        /* 1 */
        // 안드로이드 프레임웍에서 몇번째 슬라이드 화면 내놓으라고 요청할 때
        // 호출되는 함수. 이곳에서 PlaceholderFragment.newInstance(position + 1)를 사용해서
        // 그때마다 새롭게 생성함

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // 슬라이드할 화면개수를 여기서 지정한다.
            // 리턴값에 개수를 넣으면 그 만큼 슬라이딩할 화면을 만든다.
            // Show 3 total pages.
            return 3
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        /* 3 */
        // 안드로이드 프레임웍에서 화면을 만들 때, onCreateView를 호출함
        // 이곳은 마치 Activity의 onCreate() 함수와 같은 역할을 하는 곳임
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {

            // 넘겨진 파라메터를 가지고 비교분기
            // 1번 화면을 요구하면 first_layout을 가지고 화면을 만든 후,
            // 리턴한다.
            var nNumber = arguments.getInt(ARG_SECTION_NUMBER)
            if(nNumber == 1){
                val rootView = inflater.inflate(R.layout.first_layout, container, false)
                return rootView
            }

            val rootView = inflater.inflate(R.layout.fragment_main, container, false)
            rootView.section_label.text = getString(R.string.section_format, arguments.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        // java의 static 메소드를 kotlin에서는 다음과 같이 사용합니다.
        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             * <- Tag입니다. args 개체에서 tag를 주고 값을 putInt(), getInt()로 가져올 것입니다.
             */
            private val ARG_SECTION_NUMBER = "section_number"


            /* 2 */
            /**
             * Returns a new instance of this fragment for the given section
             * number.
             *
             * PlaceholderFragment를 새롭게 생성해서 리턴합니다.
             * FragmentPagerAdapter가 항목(몇번째 내놔!!)을 요청할 때마다
             * 새롭게 생성하는 것입니다. 파라메터로 Bundle이라는 객체를 만들고
             * 그 안에 숫자값으로 몇번째인지 정보를 넘깁니다.
             *
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                // PlaceholderFragment 생성
                val fragment = PlaceholderFragment()

                // Bunndle 생성
                val args = Bundle()

                // bundle(args)에 정보를 저장
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args

                // fragment(PlaceholderFragment형 변수)를 넘겨준다.
                return fragment
            }
        }
    }
}

/*
*  미션 1:  - 1, 3, 5 페이지 일 때는 이미지가 있는 화면을 보여준다(first_layout를 수정).
 *          - 이미지는 각 페이지마다 다르게 보여준다.
 *          - 2, 4, 6 페이지 일 때는 전 페이지 그림에 대한 설명을 붙여준다.
 *
 * 미션 2:  - 메일모양버튼(Floating Action Button)을 누르면 지도앱과 연동하여 원하는 주소를 보여준다.
 *
* */
