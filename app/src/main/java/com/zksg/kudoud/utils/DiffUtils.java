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

import com.netease.lib_network.entitys.ApiTokenInfo;
import com.netease.lib_network.entitys.DexScreenTokenInfo;
import com.netease.lib_network.entitys.JupToken;
import com.netease.lib_network.entitys.TransationHistoryEntity;
import com.zksg.kudoud.entitys.UiWalletToken;
import com.zksg.kudoud.entitys.WalletNetworkEntity;
import com.zksg.kudoud.utils.manager.SimpleWallet;
import com.zksg.lib_api.baby.BabyInfo;
import com.zksg.lib_api.baby.FeedTip;
import com.zksg.lib_api.beans.EnvBean;
import com.zksg.lib_api.beans.MemeBaseEntry;
import com.zksg.lib_api.playlist.BasicMusicInfo;

//import com.netease.lib_api.model.album.AlbumOrSongBean;
//import com.netease.lib_api.model.playlist.MainRecommendPlayListBean;
//import com.netease.music.data.bean.LibraryInfo;

public class DiffUtils {

    private DiffUtil.ItemCallback<BasicMusicInfo> mBasicMusicInfoItemCallback;

    private DiffUtil.ItemCallback<EnvBean> mEnvItemCallback;
    private DiffUtil.ItemCallback<FeedTip> mFeedTipItemCallback;
    private DiffUtil.ItemCallback<BabyInfo> mBabyItemCallback;

    private DiffUtil.ItemCallback<MemeBaseEntry> mMemeBaseCallback;
    private DiffUtil.ItemCallback<DexScreenTokenInfo.PairsDTO> mMemePoolCallback;
    private DiffUtil.ItemCallback<WalletNetworkEntity> mWalletNetworkEntityCallback;
    private DiffUtil.ItemCallback<SimpleWallet> mMyWalletEntityCallback;

    private DiffUtil.ItemCallback<UiWalletToken> mTokenInfoEntityCallback;

    private DiffUtil.ItemCallback<TransationHistoryEntity> mTransationHistoryEntity;
    private DiffUtil.ItemCallback<JupToken> mJubTokenItemCallback;

    private DiffUtil.ItemCallback<String> sendCoinNumberCallback;

    private DiffUtil.ItemCallback<UiWalletToken> mSearchEntityCallback;


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


    public DiffUtil.ItemCallback<MemeBaseEntry> getMemeBaseItemCallback() {
        if (mMemeBaseCallback == null) {
            mMemeBaseCallback = new DiffUtil.ItemCallback<MemeBaseEntry>() {
                @Override
                public boolean areItemsTheSame(@NonNull MemeBaseEntry oldItem, @NonNull MemeBaseEntry newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull MemeBaseEntry oldItem, @NonNull MemeBaseEntry newItem) {
                    return oldItem.getSymbol().equals(newItem.getSymbol());
                }
            };
        }
        return mMemeBaseCallback;
    }


    public DiffUtil.ItemCallback<UiWalletToken> geTokenInfoEntityCallback() {
        if (mTokenInfoEntityCallback == null) {
            mTokenInfoEntityCallback = new DiffUtil.ItemCallback<UiWalletToken>() {
                //区别2个item是否是同一个ite，一般用唯一的标识
                @Override
                public boolean areItemsTheSame(@NonNull UiWalletToken oldItem, @NonNull UiWalletToken newItem) {

                    return oldItem.equals(newItem);
                }
                //区别2个item是内容否是相同，一般用item内的属性
                @Override
                public boolean areContentsTheSame(@NonNull UiWalletToken oldItem, @NonNull UiWalletToken newItem) {
                    return oldItem.getMint().equals(newItem.getMint())&&oldItem.getBalance().equals(newItem.getBalance())&&oldItem.getPrice().equals(newItem.getPrice());
                }
            };
        }
        return mTokenInfoEntityCallback;
    }


    public DiffUtil.ItemCallback<UiWalletToken> getSearchEntityCallback() {
        if (mSearchEntityCallback == null) {
            mSearchEntityCallback = new DiffUtil.ItemCallback<UiWalletToken>() {
                //区别2个item是否是同一个ite，一般用唯一的标识
                @Override
                public boolean areItemsTheSame(@NonNull UiWalletToken oldItem, @NonNull UiWalletToken newItem) {

                    return oldItem.equals(newItem);
                }
                //区别2个item是内容否是相同，一般用item内的属性
                @Override
                public boolean areContentsTheSame(@NonNull UiWalletToken oldItem, @NonNull UiWalletToken newItem) {
                    return oldItem.getMint().equals(newItem.getMint());
                }
            };
        }
        return mSearchEntityCallback;
    }


    public DiffUtil.ItemCallback<TransationHistoryEntity> geTTransationHistoryEntity() {
        if (mTransationHistoryEntity == null) {
            mTransationHistoryEntity = new DiffUtil.ItemCallback<TransationHistoryEntity>() {
                //区别2个item是否是同一个ite，一般用唯一的标识
                @Override
                public boolean areItemsTheSame(@NonNull TransationHistoryEntity oldItem, @NonNull TransationHistoryEntity newItem) {

                    return oldItem.equals(newItem);
                }
                //区别2个item是内容否是相同，一般用item内的属性
                @Override
                public boolean areContentsTheSame(@NonNull TransationHistoryEntity oldItem, @NonNull TransationHistoryEntity newItem) {
                    return oldItem.getSender().equals(newItem.getSender())&&oldItem.getReceiver().equals(newItem.getReceiver());
                }
            };
        }
        return mTransationHistoryEntity;
    }


    public DiffUtil.ItemCallback<String> getSendCoinNumberCallback() {
        if (sendCoinNumberCallback == null) {
            sendCoinNumberCallback = new DiffUtil.ItemCallback<String>() {
                //区别2个item是否是同一个ite，一般用唯一的标识
                @Override
                public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {

                    return oldItem.equals(newItem);
                }
                //区别2个item是内容否是相同，一般用item内的属性
                @Override
                public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                    return oldItem.equals(newItem);
                }
            };
        }
        return sendCoinNumberCallback;
    }





    public DiffUtil.ItemCallback<DexScreenTokenInfo.PairsDTO> getMemePooltemCallback() {
        if (mMemePoolCallback == null) {
            mMemePoolCallback = new DiffUtil.ItemCallback<DexScreenTokenInfo.PairsDTO>() {
                @Override
                public boolean areItemsTheSame(@NonNull DexScreenTokenInfo.PairsDTO oldItem, @NonNull DexScreenTokenInfo.PairsDTO newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull DexScreenTokenInfo.PairsDTO oldItem, @NonNull DexScreenTokenInfo.PairsDTO newItem) {
                    return oldItem.getPairAddress().equals(newItem.getPairAddress());
                }
            };
        }
        return mMemePoolCallback;
    }


    public DiffUtil.ItemCallback<WalletNetworkEntity> getWalletNetworkCallback() {
        if (mWalletNetworkEntityCallback == null) {
            mWalletNetworkEntityCallback = new DiffUtil.ItemCallback<WalletNetworkEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull WalletNetworkEntity oldItem, @NonNull WalletNetworkEntity newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull WalletNetworkEntity oldItem, @NonNull WalletNetworkEntity newItem) {
                    return oldItem.getImg()==newItem.getImg();
                }
            };
        }
        return mWalletNetworkEntityCallback;
    }


    public DiffUtil.ItemCallback<SimpleWallet> getWalletCallback() {
        if (mMyWalletEntityCallback == null) {
            mMyWalletEntityCallback = new DiffUtil.ItemCallback<SimpleWallet>() {
                @Override
                public boolean areItemsTheSame(@NonNull SimpleWallet oldItem, @NonNull SimpleWallet newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull SimpleWallet oldItem, @NonNull SimpleWallet newItem) {
                    return oldItem.getAddress().equals(newItem.getAddress());
                }
            };
        }
        return mMyWalletEntityCallback;
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


    public DiffUtil.ItemCallback<JupToken> getJubTokenItemCallback() {
        if (mJubTokenItemCallback == null) {
            mJubTokenItemCallback = new DiffUtil.ItemCallback<JupToken>() {
                @Override
                public boolean areItemsTheSame(@NonNull JupToken oldItem, @NonNull JupToken newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull JupToken oldItem, @NonNull JupToken newItem) {
                    return oldItem.getAddress().equals(newItem.getAddress());
                }
            };
        }
        return mJubTokenItemCallback;
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
