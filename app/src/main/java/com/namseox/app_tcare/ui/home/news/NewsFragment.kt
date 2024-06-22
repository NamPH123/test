package com.namseox.app_tcare.ui.home.news

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.NewsModel
import com.namseox.app_tcare.databinding.FragmentNewsBinding
import com.namseox.app_tcare.ui.home.MainActivity
import com.namseox.app_tcare.ui.webview.WebViewActivity
import com.namseox.app_tcare.view.base.AbsBaseFragment

class NewsFragment : AbsBaseFragment<FragmentNewsBinding, MainActivity>(R.layout.fragment_news) {
    lateinit var adapter: NewsAdapter
    var data = arrayListOf(
        NewsModel(
            "https://www.vinmec.com/s3-images/size/xxlarge/20210523_065225_627532_dau-ha-lan-rau-tot-.max-1800x1800.jpg",
            "Rau nào tốt cho trẻ sơ sinh?",
            "https://www.vinmec.com/vi/tin-tuc/thong-tin-suc-khoe/dinh-duong/rau-nao-tot-cho-tre-so-sinh/?link_type=related_posts"
        ),
        NewsModel(
            "https://vinmec-prod.s3.amazonaws.com/images/20231128_072054_109533_benh_nhan.max-1800x1800.jpg",
            "Thành công cứu sống bệnh nhân ngừng tuần hoàn trong 9 phút cam go",
            "https://www.vinmec.com/vi/tin-tuc/thong-tin-suc-khoe/vinmec-nha-trang-thanh-cong-cuu-song-benh-nhan-ngung-tuan-hoan-trong-9-phut-cam-go/"
        ),
        NewsModel(
            "https://www.vinmec.com/s3-images/size/xxlarge/20220113_053550_460966_cach-de-an-nhieu-ra.max-1800x1800.jpg",
            "13 cách dễ dàng để ăn nhiều rau xanh hơn",
            "https://www.vinmec.com/vi/tin-tuc/thong-tin-suc-khoe/song-khoe/13-cach-de-dang-de-nhieu-rau-xanh-hon/?link_type=related_posts"
        ),
        NewsModel(
            "https://vinmec-prod.s3.amazonaws.com/images/20231025_094128_766188_Artboard_1.width-800.jpg",
            "Nâng ngực nội soi là gì?",
            "https://www.vinmec.com/vi/tin-tuc/thong-tin-suc-khoe/nang-nguc-noi-soi-la-gi/"
        ),
        NewsModel(
            "https://www.vinmec.com/s3-images/size/xxlarge/20190215_070755_419518_kham_suc_khoe_tong_.max-1800x1800.jpg",
            "Những lưu ý quan trọng trước khi khám sức khỏe tổng quát",
            "https://www.vinmec.com/vi/tin-tuc/thong-tin-suc-khoe/nhung-dieu-can-luu-y-truoc-khi-kham-suc-khoe-tong-quat/"
        ),
        NewsModel(
            "https://www.vinmec.com/s3-images/size/xxlarge/20190927_192202_324317_benh-o-than-4.max-1800x1800.jpg",
            "Phẫu thuật nội soi với Robot hỗ trợ điều trị ung thư thận",
            "https://www.vinmec.com/vi/tin-tuc/thong-tin-suc-khoe/phau-thuat-noi-soi-voi-robot-ho-tro-dieu-tri-ung-thu/"
        ),
        NewsModel(
            "https://vinmec-prod.s3.amazonaws.com/images/20191022_151608_601955_robot-phau-thuat2.max-1800x1800.jpg",
            "Phẫu thuật robot/phẫu thuật nội soi robot hỗ trợ (Robotic-assisted laparoscopy)",
            "https://www.vinmec.com/vi/tieu-hoa-gan-mat/ky-thuat-trong-diem/vi-sao-phau-thuat-robot-dang-tro-thanh-xu-huong-phau-thuat-hien-dai/?link_type=related_posts"
        ),
        NewsModel(
            "https://vinmec-prod.s3.amazonaws.com/images/20190717_074420_188660_hoa-tri-ung-thu-152.max-1800x1800.jpg",
            "Hóa trị, xạ trị, liệu pháp miễn dịch chữa ung thư ảnh hưởng tới khả năng mang thai thế nào?",
            "https://www.vinmec.com/vi/tin-tuc/thong-tin-suc-khoe/hoa-tri-xa-tri-lieu-phap-mien-dich-chua-ung-thu-anh-huong-toi-kha-nang-mang-thai-nao/"
        ),
    )

    override fun initView() {
        adapter = NewsAdapter()
        binding.rcv1.adapter = adapter
        binding.rcv1.layoutManager =
            GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        adapter.submitList(data)
        adapter.onClick = {
            startActivity(
                Intent(requireContext(), WebViewActivity::class.java).putExtra(
                    "data",
                    data[it].uri
                )
            )
        }
    }

    override fun setView() {

    }

    override fun setClick() {
    }
}