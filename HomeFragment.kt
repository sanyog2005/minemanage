package com.example.minemanage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.minemanage.adapters.AdapterCategory
import com.example.minemanage.databinding.FragmentHomeBinding
import com.example.minemanage.models.Category


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setAllCategories()



        return binding.root
    }

    private fun setAllCategories() {
        val categoryList = ArrayList<Category>()
        for (i in 0 until  Constant.allProductCategoryIcon.size){
            categoryList.add(Category(Constant.allProductCategory[i],Constant.allProductCategoryIcon[i]))
        }
        binding.rvcategories.adapter = AdapterCategory(categoryList)
    }


}