package com.cherry.convenientbanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cherry.convenientbanner.databinding.FragmentFirstBinding
import com.cherry.library.convenientbanner.ConvenientBanner
import com.cherry.library.convenientbanner.holder.CBViewHolderCreator

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        var images = ArrayList<Int>()
        images.add(R.mipmap.ic_test_0)
        images.add(R.mipmap.ic_test_1)
        images.add(R.mipmap.ic_test_2)
        images.add(R.mipmap.ic_test_3)
        images.add(R.mipmap.ic_test_4)
        images.add(R.mipmap.ic_test_5)
        images.add(R.mipmap.ic_test_6)

        showBannerData(images, binding.mCbBanner as ConvenientBanner<String>,R.layout.home_banner_cell)
    }


    fun showBannerData(data: List<Int>?, bannerView: ConvenientBanner<String>, bannerCellResId: Int) {
        if (data == null) {
            bannerView.visibility = View.GONE
            return
        }
        if (data?.size == 0) {
            bannerView.visibility = View.GONE
            return
        }

        bannerView.visibility = View.VISIBLE
        var indicator = ArrayList<Int>()
        var bannerCount = data.size ?: 0
        if (bannerCount > 1) {
            bannerView?.startTurning(5000)
            indicator.add(R.mipmap.ic_page_indicator)
            indicator.add(R.mipmap.ic_page_indicator_focused)
        } else {
            indicator.add(0)
            indicator.add(0)
        }

        bannerView.setPages(object : CBViewHolderCreator {
            override fun createHolder(itemView: View?): LocalImageHolderView {
                return LocalImageHolderView(itemView)
            }

            override fun getLayoutId(): Int {
                return bannerCellResId
            }
        }, data as List<Nothing>?) //设置指示器的方向
            //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
            ?.setPageIndicator(indicator.toIntArray())
            ?.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
            ?.setOnItemClickListener {
            }.apply {
                bannerView.setCanLoop(bannerCount > 1)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}