/*
 * Copyright 2018-present KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zksg.kudoud.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.zksg.lib_api.baby.BabyInfo;
import com.zksg.lib_api.baby.FeedTip;
import com.zksg.lib_api.beans.EnvBean;
import com.zksg.lib_api.playlist.BasicMusicInfo;

//import com.netease.lib_api.model.album.AlbumOrSongBean;
//import com.netease.lib_api.model.playlist.MainRecommendPlayListBean;
//import com.netease.music.data.bean.LibraryInfo;

public class DiffUtils {

    private DiffUtil.ItemCallback<BasicMusicInfo> mBasicMusicInfoItemCallback;

    private DiffUtil.ItemCallback<EnvBean> mEnvItemCallback;
    private DiffUtil.ItemCallback<FeedTip> mFeedTipItemCallback;
    private DiffUtil.ItemCallback<BabyInfo> mBabyItemCallback;

//    private DiffUtil.ItemCallback<MainRecommendPlayListBean.RecommendBean> mRecommendPlaylistItemCallback;
//
//    private DiffUtil.ItemCallback<AlbumOrSongBean> mAlbumOrSongItemCallback;

    private DiffUtils() {
    }

    private final static DiffUtils sDiffUtils = new DiffUtils();

    public static DiffUtils getInstance() {
        return sDiffUtils;
    }

    public DiffUtil.ItemCallback<BasicMusicInfo> getBasicMusicInfoItemCallback() {
        if (mBasicMusicInfoItemCallback == null) {
            mBasicMusicInfoItemCallback = new DiffUtil.ItemCallback<BasicMusicInfo>() {
                @Override
                public boolean areItemsTheSame(@NonNull BasicMusicInfo oldItem, @NonNull BasicMusicInfo newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull BasicMusicInfo oldItem, @NonNull BasicMusicInfo newItem) {
                    return oldItem.getName().equals(newItem.getName())&&oldItem.getLeng().equals(newItem.getLeng());
                }
            };
        }
        return mBasicMusicInfoItemCallback;
    }

    public DiffUtil.ItemCallback<EnvBean> getEnvItemCallback() {
        if (mEnvItemCallback == null) {
            mEnvItemCallback = new DiffUtil.ItemCallback<EnvBean>() {
                @Override
                public boolean areItemsTheSame(@NonNull EnvBean oldItem, @NonNull EnvBean newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull EnvBean oldItem, @NonNull EnvBean newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }
            };
        }
        return mEnvItemCallback;
    }


    public DiffUtil.ItemCallback<FeedTip> getFeedTipItemCallback() {
        if (mFeedTipItemCallback == null) {
            mFeedTipItemCallback = new DiffUtil.ItemCallback<FeedTip>() {
                @Override
                public boolean areItemsTheSame(@NonNull FeedTip oldItem, @NonNull FeedTip newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull FeedTip oldItem, @NonNull FeedTip newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }
            };
        }
        return mFeedTipItemCallback;
    }


    public DiffUtil.ItemCallback<BabyInfo> getBabyItemCallback() {
        if (mBabyItemCallback == null) {
            mBabyItemCallback = new DiffUtil.ItemCallback<BabyInfo>() {
                @Override
                public boolean areItemsTheSame(@NonNull BabyInfo oldItem, @NonNull BabyInfo newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull BabyInfo oldItem, @NonNull BabyInfo newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }
            };
        }
        return mBabyItemCallback;
    }


//    public DiffUtil.ItemCallback<MainRecommendPlayListBean.RecommendBean> getRecommendPlayListItemCallback() {
//        if (mRecommendPlaylistItemCallback == null) {
//            mRecommendPlaylistItemCallback = new DiffUtil.ItemCallback<MainRecommendPlayListBean.RecommendBean>() {
//                @Override
//                public boolean areItemsTheSame(@NonNull MainRecommendPlayListBean.RecommendBean oldItem, @NonNull MainRecommendPlayListBean.RecommendBean newItem) {
//                    return oldItem.equals(newItem);
//                }
//
//                @Override
//                public boolean areContentsTheSame(@NonNull MainRecommendPlayListBean.RecommendBean oldItem, @NonNull MainRecommendPlayListBean.RecommendBean newItem) {
//                    return oldItem.getId() == newItem.getId();
//                }
//            };
//        }
//        return mRecommendPlaylistItemCallback;
//    }

//    public DiffUtil.ItemCallback<AlbumOrSongBean> getAlbumOrSongItemCallback() {
//        if (mAlbumOrSongItemCallback == null) {
//            mAlbumOrSongItemCallback = new DiffUtil.ItemCallback<AlbumOrSongBean>() {
//                @Override
//                public boolean areItemsTheSame(@NonNull AlbumOrSongBean oldItem, @NonNull AlbumOrSongBean newItem) {
//                    return oldItem.equals(newItem);
//                }
//
//                @Override
//                public boolean areContentsTheSame(@NonNull AlbumOrSongBean oldItem, @NonNull AlbumOrSongBean newItem) {
//                    return oldItem.getId() == newItem.getId();
//                }
//            };
//        }
//        return mAlbumOrSongItemCallback;
//    }
}
