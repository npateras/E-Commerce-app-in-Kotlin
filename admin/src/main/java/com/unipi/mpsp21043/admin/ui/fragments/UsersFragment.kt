package com.unipi.mpsp21043.admin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.unipi.mpsp21043.admin.adapters.UsersListAdapter
import com.unipi.mpsp21043.admin.database.FirestoreHelper
import com.unipi.mpsp21043.admin.databinding.FragmentUsersBinding
import com.unipi.mpsp21043.admin.models.User


class UsersFragment : Fragment() {

    // ~~~~~~~VARIABLES~~~~~~~
    // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
    private var _binding: FragmentUsersBinding? = null  // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var usersListAdapter: UsersListAdapter
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)

        init()

        return binding.root
    }

    private fun init() {
        showShimmerUI()

        getUsers()
    }

    private fun getUsers() {
        FirestoreHelper().getUsersList(this@UsersFragment)
    }

    /**
     * A function to get the successful product list from cloud firestore.
     *
     * @param usersList Will receive the product list from cloud firestore.
     */
    fun successUsersListFromFirestore(usersList: ArrayList<User>) {

        if (usersList.size > 0) {
            hideShimmerUI()

            usersListAdapter = UsersListAdapter(
                requireActivity(),
                usersList
            )
            // Sets RecyclerView's properties
            binding.recyclerViewItems.run {
                adapter = usersListAdapter
                hasFixedSize()
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            }
        }
        else
            showEmptyStateUI()
    }

    private fun showShimmerUI() {
        binding.apply {
            layoutEmptyState.root.visibility = View.GONE
            recyclerViewItems.visibility = View.GONE
            shimmerViewContainer.visibility = View.VISIBLE
            shimmerViewContainer.startShimmer()
        }
    }

    private fun hideShimmerUI() {
        binding.apply {
            layoutEmptyState.root.visibility = View.GONE
            recyclerViewItems.visibility = View.VISIBLE
            shimmerViewContainer.visibility = View.GONE
            shimmerViewContainer.stopShimmer()
        }
    }

    private fun showEmptyStateUI() {
        binding.apply {
            layoutEmptyState.root.visibility = View.VISIBLE
            recyclerViewItems.visibility = View.GONE
            shimmerViewContainer.visibility = View.GONE
            shimmerViewContainer.stopShimmer()
        }
    }

    /**
     * The fragment's onResume() will be called only when the Activity's onResume() is called.
     */
    override fun onResume() {
        super.onResume()
        init()
    }

    /**
     * We clean up any references to the binding class instance in the fragment's onDestroyView() method.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
