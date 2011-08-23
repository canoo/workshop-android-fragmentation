package com.canoo.workshop.android.tablet.rss.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.webkit.WebView;
import android.widget.TextView;
import com.canoo.workshop.android.tablet.rss.fragments.R;

/**
 * @author Andrei Socaciu
 */
public class RssDetailFragment extends Fragment {

    private TextView fTitle;
    private TextView fAuthor;
    private TextView fCategories;
    private WebView fContent;
    private RssItem fRssItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.rss_detail, container, false);


        fTitle = (TextView) fragmentView.findViewById(R.id.RssItemTitle);
        fAuthor = (TextView) fragmentView.findViewById(R.id.RssItemAuthor);
        fCategories = (TextView) fragmentView.findViewById(R.id.RssItemCategories);
        fContent = (WebView) fragmentView.findViewById(R.id.RssItemContent);

        fTitle.setVisibility(View.INVISIBLE);
        fAuthor.setVisibility(View.INVISIBLE);
        fCategories.setVisibility(View.INVISIBLE);
        fContent.setVisibility(View.INVISIBLE);

        fContent.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                fContent.startActionMode(new ActionMode.Callback() {
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        actionMode.setTitle(fTitle.getText());
                        getActivity().getMenuInflater().inflate(R.menu.rss_detail_menu, menu);
                        return true;
                    }

                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        return false;
                    }

                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        if (fRssItem != null) {
                            Intent intent = new Intent(Intent.ACTION_SEND)
                                    .putExtra(Intent.EXTRA_STREAM, fRssItem.content)
                                    .setType("text/html");
                            startActivity(Intent.createChooser(intent, "Share Article"));

                            return true;
                        }
                        return false;
                    }

                    public void onDestroyActionMode(ActionMode actionMode) {
                    }
                });
                return true;
            }
        });

        return fragmentView;
    }

    void setRssItem(RssItem item) {
        fRssItem = item;
        fTitle.setText(item.title);
        fContent.loadDataWithBaseURL("http://www.canoo.com/", wrapContentWithStyle(item), "text/html", "UTF-8", null);
        fAuthor.setText("Posted by " + item.author + " on " + RssFragment.DATE_FORMAT.format(item.publishDate));
        fCategories.setText(item.categories.toString());

        fTitle.setVisibility(View.VISIBLE);
        fAuthor.setVisibility(View.VISIBLE);
        fCategories.setVisibility(View.VISIBLE);
        fContent.setVisibility(View.VISIBLE);
    }

    private String wrapContentWithStyle(RssItem item) {
        return "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<link rel=\"stylesheet\" href=\"file:///android_asset/style.css\" type=\"text/css\"/>\n" +
                "</head>\n" +
                "<body>\n" +
                item.content +
                "</body>\n" +
                "</html>";
    }
}
