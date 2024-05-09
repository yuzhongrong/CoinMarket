package com.zksg.kudoud.activitys

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.netease.lib_network.entitys.JupToken
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.CoinManagerListdapter
import com.zksg.kudoud.adapters.CoinManagerLocalTokensdapter
import com.zksg.kudoud.adapters.CoinManagerSearchTokensdapter
import com.zksg.kudoud.contants.GlobalConstant
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.state.CoinManagerActivityViewModel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.IntentUtils
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.SearchTokenUtils
import com.zksg.kudoud.utils.TokenConverter
import com.zksg.kudoud.wallet.constants.Constants

class CoinManagerActivity : BaseActivity() {
    var viewModel: CoinManagerActivityViewModel? = null
    var mSharedViewModel: SharedViewModel? = null
    var keyAlias: String? = null
    override fun initViewModel() {
        viewModel = getActivityScopeViewModel(
            CoinManagerActivityViewModel::class.java
        )
        mSharedViewModel = getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_coin_manager, BR.vm, viewModel!!)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.hotadapter, CoinManagerListdapter(this))
            .addBindingParam(BR.addedadapter, CoinManagerLocalTokensdapter(this))
            .addBindingParam(BR.searchadapter, CoinManagerSearchTokensdapter(this))
            .addBindingParam(BR.editfocus, onEditFocusChangeListener)
            .addBindingParam(BR.searchTextWatcher, onSearchTextWatcher)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        keyAlias = intent.getStringExtra("keyAlias")
        initObserve(keyAlias)
        initData(keyAlias)
    }

    private fun initObserve(keyAlias: String?) {

        //订阅添加
        viewModel!!.localdatas.observe(this) { uiWalletToken: List<UiWalletToken?>? ->
            //保存自己到本地即可
            val newLocalDatas = viewModel!!.localdatas.value!!
            try {
                val newLocalDatasbytes = ObjectSerializationUtils.serializeObject(newLocalDatas)
                MMKV.mmkvWithID(Constants.UI_TOKENS).encode(keyAlias, newLocalDatasbytes)
                mSharedViewModel!!.requestAddToken(true)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }

    private fun initData(keyAlias: String?) {
        try {
            //1.先初始化本地已有的币种列表
            val tokensbytes = MMKV.mmkvWithID(Constants.UI_TOKENS).decodeBytes(keyAlias)
            val tokens: List<UiWalletToken> =
                ObjectSerializationUtils.deserializeObject(tokensbytes) as ArrayList<UiWalletToken>
            viewModel!!.localdatas.postValue(tokens)
            val hotdatas = initHots(tokens)
            initSearchData(tokens, hotdatas)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun initHots(localdatas: List<UiWalletToken>?): List<UiWalletToken> {

        //2.初始化热门代币列表
        /**本地摸你json数据 流程： MainActivity里面 请求api服务拿到总数据源--->生成热门数据源--->保存总数据源,保存热门数据源 ，这里提取的是热门数据源
         *
         */
//        String jub_str="[{\"address\":\"So11111111111111111111111111111111111111112\",\"chainId\":101,\"decimals\":9,\"name\":\"Wrapped SOL\",\"symbol\":\"SOL\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/So11111111111111111111111111111111111111112/logo.png\",\"tags\":[\"old-registry\"],\"extensions\":{\"coingeckoId\":\"wrapped-solana\"}},{\"address\":\"EPjFWdd5AufqSSqeM2qN1xzybapC8G4wEGGkZwyTDt1v\",\"chainId\":101,\"decimals\":6,\"name\":\"USD Coin\",\"symbol\":\"USDC\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/EPjFWdd5AufqSSqeM2qN1xzybapC8G4wEGGkZwyTDt1v/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"usd-coin\"}},{\"address\":\"Es9vMFrzaCERmJfrF4H2FYD4KCoNkY11McCe8BenwNYB\",\"chainId\":101,\"decimals\":6,\"name\":\"USDT\",\"symbol\":\"USDT\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/Es9vMFrzaCERmJfrF4H2FYD4KCoNkY11McCe8BenwNYB/logo.svg\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"tether\"}},{\"address\":\"EKpQGSJtjMFqKZ9KQanSqYXRcF8fBopzLHYxdM65zcjm\",\"chainId\":101,\"decimals\":6,\"name\":\"dogwifhat\",\"symbol\":\"$WIF\",\"logoURI\":\"https://bafkreibk3covs5ltyqxa272uodhculbr6kea6betidfwy3ajsav2vjzyum.ipfs.nftstorage.link\",\"tags\":[\"community\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"dogwifcoin\"}},{\"address\":\"JUPyiwrYJFskUPiHa7hkeR8VUtAeFoSYbKedZNsDvCN\",\"chainId\":101,\"decimals\":6,\"name\":\"Jupiter\",\"symbol\":\"JUP\",\"logoURI\":\"https://static.jup.ag/jup/icon.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"jupiter-exchange-solana\"}},{\"address\":\"7GCihgDB8fe6KNjn2MYtkzZcRjQy3t9GHdC8uHYmW2hr\",\"chainId\":101,\"decimals\":9,\"name\":\"Popcat\",\"symbol\":\"POPCAT\",\"logoURI\":\"https://bafkreidvkvuzyslw5jh5z242lgzwzhbi2kxxnpkic5wsvyno5ikvpr7reu.ipfs.nftstorage.link\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"popcat\"}},{\"address\":\"26KMQVgDUoB6rEfnJ51yAABWWJND8uMtpnQgsHQ64Udr\",\"chainId\":101,\"decimals\":6,\"name\":\"SAD HAMSTER\",\"symbol\":\"HAMMY\",\"logoURI\":\"https://bafkreidnhl3agzjkay3ljojmvwid72amoumqftgaw7lks2keg27ph3efsu.ipfs.nftstorage.link\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"sad-hamster\"}},{\"address\":\"J1toso1uCk3RLmjorhTtrVwY9HJ7X8V9yYac6Y7kGCPn\",\"chainId\":101,\"decimals\":9,\"name\":\"Jito Staked SOL\",\"symbol\":\"JitoSOL\",\"logoURI\":\"https://storage.googleapis.com/token-metadata/JitoSOL-256.png\",\"tags\":[\"community\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"jito-staked-sol\"}},{\"address\":\"3psH1Mj1f7yUfaD5gh6Zj7epE8hhrMkMETgv5TshQA4o\",\"chainId\":101,\"decimals\":9,\"name\":\"jeo boden\",\"symbol\":\"boden\",\"logoURI\":\"https://bafkreid2t4f3i36tq4aowwaaa5633ggslefthxfdudaimog6unwu36umha.ipfs.nftstorage.link/\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"jeo-boden\"}},{\"address\":\"27G8MtK7VtTcCHkpASjSDdkWWYfoqT6ggEuKidVJidD4\",\"chainId\":101,\"decimals\":6,\"name\":\"Jupiter Perps\",\"symbol\":\"JLP\",\"logoURI\":\"https://static.jup.ag/jlp/icon.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"jupiter-perpetuals-liquidity-provider-token\"}},{\"address\":\"5mbK36SZ7J19An8jFochhQS4of8g6BwUjbeCSxBSoWdp\",\"chainId\":101,\"decimals\":6,\"name\":\"michi\",\"symbol\":\"$michi\",\"logoURI\":\"https://i.ibb.co/GxG0314/5mb-K36-SZ7-J19-An8j-Fochh-QS4of8g6-Bw-Ujbe-CSx-BSo-Wdp.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"michicoin\"}},{\"address\":\"DezXAZ8z7PnrnRJjz3wXBoRgixCa6xjnB7YaB1pPB263\",\"chainId\":101,\"decimals\":5,\"name\":\"Bonk\",\"symbol\":\"Bonk\",\"logoURI\":\"https://arweave.net/hQiPZOsRZXGXBJd_82PhVdlM_hACsT_q6wqwf5cSY7I?ext=png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"bonk\"}},{\"address\":\"mSoLzYCxHdYgdzU16g5QSh3i5K3z3KZK7ytfqcJm7So\",\"chainId\":101,\"decimals\":9,\"name\":\"Marinade staked SOL (mSOL)\",\"symbol\":\"mSOL\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/mSoLzYCxHdYgdzU16g5QSh3i5K3z3KZK7ytfqcJm7So/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"msol\"}},{\"address\":\"5oVNBeEEQvYi1cX3ir8Dx5n1P7pdxydbGF2X4TxVusJm\",\"chainId\":101,\"decimals\":9,\"name\":\"Infinity\",\"symbol\":\"INF\",\"logoURI\":\"https://cloudflare-ipfs.com/ipfs/bafkreiflz2xxkfn33qjch2wj55bvbn33q3s4mmb6bye5pt3mpgy4t2wg4e\",\"tags\":[\"old-registry\"],\"extensions\":{\"coingeckoId\":\"socean-staked-sol\"}},{\"address\":\"6D7NaB2xsLd7cauWu1wKk6KBsJohJmP2qZH9GEfVi5Ui\",\"chainId\":101,\"decimals\":6,\"name\":\"Shark Cat\",\"symbol\":\"SC\",\"logoURI\":\"https://cf-ipfs.com/ipfs/QmYZnjijjtoH2YDCPxUc6advSuSbsCre4gDjtS2YTUfw7P\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"shark-cat\"}},{\"address\":\"25hAyBQfoDhfWx9ay6rarbgvWGwDdNqcHsXS3jQ3mTDJ\",\"chainId\":101,\"decimals\":5,\"name\":\"MANEKI\",\"symbol\":\"MANEKI\",\"logoURI\":\"https://img.fotofolio.xyz/?url=https%3A%2F%2Fi.ibb.co%2FJnz56gp%2FManeki-PFP.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"maneki\"}},{\"address\":\"GtDZKAqvMZMnti46ZewMiXCa4oXF4bZxwQPoKzXPFxZn\",\"chainId\":101,\"decimals\":9,\"name\":\"nubcat\",\"symbol\":\"nub\",\"logoURI\":\"https://bafkreieny7bfqv76t3pgaaktrrux6j2iflefncegqxmezqsqrzy7kjhhy4.ipfs.nftstorage.link/\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"sillynubcat\"}},{\"address\":\"bSo13r4TkiE4KumL71LsHTPpL2euBYLFx6h9HP3piy1\",\"chainId\":101,\"decimals\":9,\"name\":\"BlazeStake Staked SOL (bSOL)\",\"symbol\":\"bSOL\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/bSo13r4TkiE4KumL71LsHTPpL2euBYLFx6h9HP3piy1/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"blazestake-staked-sol\"}},{\"address\":\"69kdRLyP5DTRkpHraaSZAQbWmAwzF9guKjZfzMXzcbAs\",\"chainId\":101,\"decimals\":6,\"name\":\"American Coin\",\"symbol\":\"USA\",\"logoURI\":\"https://arweave.net/xUs-YuP__T2cCUofTOJmYcHIzFHj5s8TdH-O-g9qn3w\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"american-coin\"}},{\"address\":\"jupSoLaHXQiZZTSfEWMTRRgpnyFm8f6sZdosWBjx93v\",\"chainId\":101,\"decimals\":9,\"name\":\"Jupiter Staked SOL\",\"symbol\":\"JupSOL\",\"logoURI\":\"https://static.jup.ag/jupSOL/icon.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"jupiter-staked-sol\"}},{\"address\":\"AMjzRn1TBQwQfNAjHFeBb7uGbbqbJB7FzXAnGgdFPk6K\",\"chainId\":101,\"decimals\":6,\"name\":\"SolCex\",\"symbol\":\"SOLCEX\",\"logoURI\":\"https://i.imgur.com/YN1yUMM.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"solcex\"}},{\"address\":\"MEW1gQWJ3nEXg2qgERiKu7FAFj79PHvQVREQUzScPP5\",\"chainId\":101,\"decimals\":5,\"name\":\"cat in a dogs world\",\"symbol\":\"MEW\",\"logoURI\":\"https://bafkreidlwyr565dxtao2ipsze6bmzpszqzybz7sqi2zaet5fs7k53henju.ipfs.nftstorage.link/\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"cat-in-a-dogs-world\"}},{\"address\":\"7BgBvyjrZX1YKz4oh9mjb8ZScatkkwb8DzFx7LoiVkM3\",\"chainId\":101,\"decimals\":9,\"name\":\"SLERF\",\"symbol\":\"SLERF\",\"logoURI\":\"https://bafkreih44n5jgqpwuvimsxzroyebjunnm47jttqusb4ivagw3vsidil43y.ipfs.nftstorage.link/\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"slerf\"}},{\"address\":\"5z3EqYQo9HiCEs3R84RCDMu2n7anpDMxRhdK8PSWmrRC\",\"chainId\":101,\"decimals\":9,\"name\":\"PONKE\",\"symbol\":\"PONKE\",\"logoURI\":\"https://i.imgur.com/qgEcBin.jpg\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"ponke\"}},{\"address\":\"ukHH6c7mMyiWCf1b9pnWe25TSpkDDt3H5pQZgZ74J82\",\"chainId\":101,\"decimals\":6,\"name\":\"BOOK OF MEME\",\"symbol\":\"BOME\",\"logoURI\":\"https://bafybeidov7gddabmqke3fozpuvlllp3q2c537f2vfyyf6or4spbbao6cee.ipfs.nftstorage.link/\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"book-of-meme\"}},{\"address\":\"rndrizKT3MK1iimdxRdWabcF7Zg7AR5T4nud4EkHBof\",\"chainId\":101,\"decimals\":8,\"name\":\"Render Token\",\"symbol\":\"RENDER\",\"logoURI\":\"https://shdw-drive.genesysgo.net/5zseP54TGrcz9C8HdjZwJJsZ6f3VbP11p1abwKWGykZH/rndr.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"render-token\"}},{\"address\":\"WENWENvqqNya429ubCdR81ZmD69brwQaaBYY6p3LCpk\",\"chainId\":101,\"decimals\":5,\"name\":\"Wen\",\"symbol\":\"WEN\",\"logoURI\":\"https://shdw-drive.genesysgo.net/GwJapVHVvfM4Mw4sWszkzywncUWuxxPd6s9VuFfXRgie/wen_logo.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"wen-4\"}},{\"address\":\"H7bTHGb5Cvo5fGe5jBDNDPUv8KykQnzyZA3qZ8sH7yxw\",\"chainId\":101,\"decimals\":9,\"name\":\"GUMMY\",\"symbol\":\"GUMMY\",\"logoURI\":\"https://bafkreih3pz2wklsnoae5zuyfvascdm3phyffimvyxbsi34rjnqzkjldopu.ipfs.nftstorage.link\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"gummy\"}},{\"address\":\"KMNo3nJsBXfcpJTVhZcXLW7RmTwTt4GVFE7suUBo9sS\",\"chainId\":101,\"decimals\":6,\"name\":\"Kamino\",\"symbol\":\"KMNO\",\"logoURI\":\"https://cdn.kamino.finance/kamino.svg\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"kamino\"}},{\"address\":\"85VBFQZC9TZkfaptBWjvUw7YbZjy52A6mjtPGjstQAmQ\",\"chainId\":101,\"decimals\":6,\"name\":\"Wormhole Token\",\"symbol\":\"W\",\"logoURI\":\"https://wormhole.com/token.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"wormhole\"}},{\"address\":\"3NZ9JMVBmGAqocybic2c7LQCJScmgsAZ6vQqTDzcqmJh\",\"chainId\":101,\"decimals\":8,\"name\":\"Wrapped BTC (Portal)\",\"symbol\":\"WBTC\",\"logoURI\":\"https://raw.githubusercontent.com/wormhole-foundation/wormhole-token-list/main/assets/WBTC_wh.png\",\"tags\":[\"wormhole\",\"community\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"wrapped-btc-wormhole\"}},{\"address\":\"7atgF8KQo4wJrD5ATGX7t1V2zVvykPJbFfNeVf1icFv1\",\"chainId\":101,\"decimals\":2,\"name\":\"catwifhat\",\"symbol\":\"$CWIF\",\"logoURI\":\"https://i.postimg.cc/d1QD417z/200x200logo-copy.jpg\",\"tags\":[\"community\",\"token-2022\"],\"extensions\":{\"coingeckoId\":\"catwifhat-2\"}},{\"address\":\"SHDWyBxihqiCj6YekG2GUr7wqKLeLAMK1gHZck9pL6y\",\"chainId\":101,\"decimals\":9,\"name\":\"Shadow Token\",\"symbol\":\"SHDW\",\"logoURI\":\"https://shdw-drive.genesysgo.net/FDcC9gn12fFkSU2KuQYH4TUjihrZxiTodFRWNF4ns9Kt/250x250_with_padding.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"genesysgo-shadow\"}},{\"address\":\"jtojtomepa8beP8AuQc6eXt5FriJwfFMwQx2v2f9mCL\",\"chainId\":101,\"decimals\":9,\"name\":\"JITO\",\"symbol\":\"JTO\",\"logoURI\":\"https://metadata.jito.network/token/jto/image\",\"tags\":[\"community\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"jito-governance-token\"}},{\"address\":\"nosXBVoaCTtYdLvKY6Csb4AC8JCdQKKAaWYtx2ZMoo7\",\"chainId\":101,\"decimals\":6,\"name\":\"Nosana\",\"symbol\":\"NOS\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/nosXBVoaCTtYdLvKY6Csb4AC8JCdQKKAaWYtx2ZMoo7/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"nosana\"}},{\"address\":\"HhJpBhRRn4g56VsyLuT8DL5Bv31HkXqsrahTTUCZeZg4\",\"chainId\":101,\"decimals\":9,\"name\":\"Myro\",\"symbol\":\"$MYRO\",\"logoURI\":\"https://i.ibb.co/9nr3xFp/MYRO-200x200.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"myro\"}},{\"address\":\"7vfCXTUXx5WJV5JADk17DUJ4ksgau7utNKj4b963voxs\",\"chainId\":101,\"decimals\":8,\"name\":\"Ether (Portal)\",\"symbol\":\"ETH\",\"logoURI\":\"https://raw.githubusercontent.com/wormhole-foundation/wormhole-token-list/main/assets/ETH_wh.png\",\"tags\":[\"wormhole\",\"old-registry\"],\"extensions\":{\"coingeckoId\":\"ethereum-wormhole\"}},{\"address\":\"LSTxxxnJzKDFSLr4dUkPcmCf5VyryEqzPLz5j4bpxFp\",\"chainId\":101,\"decimals\":9,\"name\":\"Liquid Staking Token\",\"symbol\":\"LST\",\"logoURI\":\"https://storage.googleapis.com/static-marginfi/lst.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"liquid-staking-token\"}},{\"address\":\"9SiKU8vnRiBYQSBff84K5zwG7habzwYVzn7KrtgCzNfg\",\"chainId\":101,\"decimals\":9,\"name\":\"Pedro the Raccoon\",\"symbol\":\"PEDRO\",\"logoURI\":\"https://bafybeifphw5w3ygiod2abdd7oipul5uw4e5c74g2wo6btcou7yjosxapiq.ipfs.nftstorage.link/\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"pedro-the-raccoon\"}},{\"address\":\"3WPep4ufaToK1aS5s8BL9inzeUrt4DYaQCiic6ZkkC1U\",\"chainId\":101,\"decimals\":9,\"name\":\"Giko Cat\",\"symbol\":\"GIKO\",\"logoURI\":\"https://bafkreihtk4j4mjgragx3t2tqcjlhvq4w6mqn23bbapkakxmui4ghpuysdy.ipfs.nftstorage.link/\",\"tags\":[\"community\"]},{\"address\":\"Fch1oixTPri8zxBnmdCEADoJW2toyFHxqDZacQkwdvSP\",\"chainId\":101,\"decimals\":9,\"name\":\"HARAMBE\",\"symbol\":\"HARAMBE\",\"logoURI\":\"https://turquoise-worried-llama-208.mypinata.cloud/ipfs/QmSsf1AHHx56hyR66DrfvohdQ9mwNrXcZc3WYZb3xi9Tfs?pinataGatewayToken=IjmxGjT2wDVCW9f1Vtqqu4mFAuFmeTtF2KuGOPOUmuNDv0IzHYWPG90almZJn1qp\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"harambe-2\"}},{\"address\":\"CQSzJzwW5H1oyWrp6QhfUKYYwyovbSiVDKnAxNfb1tJC\",\"chainId\":101,\"decimals\":5,\"name\":\"Stanley Cup Coin\",\"symbol\":\"STAN\",\"logoURI\":\"https://arweave.net/urnvj3NcnKmwEWNLDQJetp2OFP_nu3tB8HPdXwSFg7U\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"stanley-cup-coin\"}},{\"address\":\"ZEUS1aR7aX8DFFJf5QjWj2ftDDdNTroMNGo8YoQm3Gq\",\"chainId\":101,\"decimals\":6,\"name\":\"ZEUS\",\"symbol\":\"ZEUS\",\"logoURI\":\"https://raw.githubusercontent.com/ZeusNetworkHQ/zeus-metadata/master/logo-v1.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"zeus-network\"}},{\"address\":\"Av6qVigkb7USQyPXJkUvAEm4f599WTRvd75PUWBA9eNm\",\"chainId\":101,\"decimals\":9,\"name\":\"Costco Hot Dog\",\"symbol\":\"COST\",\"logoURI\":\"https://ipfs.io/ipfs/QmSqddq1svm7VpaLCcSQzCea58J8xRo8vhHuVJgdRPUw1J\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"costco-hot-dog\"}},{\"address\":\"4pb6mymm9hYQN6og9uF24eyZ2qwXCWCwGvcR1DkCgeEr\",\"chainId\":101,\"decimals\":6,\"name\":\"NutFlex\",\"symbol\":\"NUT\",\"logoURI\":\"https://cf-ipfs.com/ipfs/QmPC2j7zQaeW1u8HDXWh1uWD26Q12GxVgc8nNATzk8ZLFJ\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"nutflex\"}},{\"address\":\"oreoN2tQbHXVaZsr3pf66A48miqcBXCDJozganhEJgz\",\"chainId\":101,\"decimals\":9,\"name\":\"Ore\",\"symbol\":\"ORE\",\"logoURI\":\"https://ore.supply/icon.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"ore\"}},{\"address\":\"AVLhahDcDQ4m4vHM4ug63oh7xc8Jtk49Dm5hoe9Sazqr\",\"chainId\":101,\"decimals\":6,\"name\":\"Solama\",\"symbol\":\"SOLAMA\",\"logoURI\":\"https://gateway.irys.xyz/UoPHSd2rbj9krhwHfR4Vk94JnzgwbDs8SW92_Z90YUM\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"solama\"}},{\"address\":\"HZ1JovNiVvGrGNiiYvEozEVgZ58xaU3RKwX8eACQBCt3\",\"chainId\":101,\"decimals\":6,\"name\":\"Pyth Network\",\"symbol\":\"PYTH\",\"logoURI\":\"https://pyth.network/token.svg\",\"tags\":[\"community\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"pyth-network\"}},{\"address\":\"947tEoG318GUmyjVYhraNRvWpMX7fpBTDQFBoJvSkSG3\",\"chainId\":101,\"decimals\":9,\"name\":\"Solchat\",\"symbol\":\"CHAT\",\"logoURI\":\"https://bafybeigqw6dl5tiexa7utfhcsgldalhrelvjnea23jckqsgo65i2uoln5i.ipfs.nftstorage.link\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"solchat\"}},{\"address\":\"5LafQUrVco6o7KMz42eqVEJ9LW31StPyGjeeu5sKoMtA\",\"chainId\":101,\"decimals\":6,\"name\":\"Mumu the Bull\",\"symbol\":\"MUMU\",\"logoURI\":\"https://bafkreihszutctvdmdlyjtzfmj7rgvdorpc7jchj2td3feypc7veidbkpsu.ipfs.nftstorage.link\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"mumu-the-bull-3\"}},{\"address\":\"AoN2z7w7ccQJQiWS7rjS45dcyYkVkBddXDcrzmj69tqf\",\"chainId\":101,\"decimals\":3,\"name\":\"Robert\",\"symbol\":\"ROBERT\",\"logoURI\":\"https://raw.githubusercontent.com/scoops0/Robert-Logo/main/Robert%20Logo.webp\",\"tags\":[\"community\"]},{\"address\":\"3vHSsV6mgvpa1JVuuDZVB72vYbeUNzW4mBxiBftwzHEA\",\"chainId\":101,\"decimals\":9,\"name\":\"Final Frontier\",\"symbol\":\"FRNT\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/3vHSsV6mgvpa1JVuuDZVB72vYbeUNzW4mBxiBftwzHEA/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"]},{\"address\":\"BLKFNuCET9ZnmhFczGXmcWKSap9kGtTFnosvA9P5majk\",\"chainId\":101,\"decimals\":9,\"name\":\"Blackfin AI\",\"symbol\":\"BLKFN\",\"logoURI\":\"https://i.imgur.com/8I4YAIl.png\",\"tags\":[\"community\"]},{\"address\":\"GDfnEsia2WLAW5t8yx2X5j2mkfA74i5kwGdDuZHt7XmG\",\"chainId\":101,\"decimals\":9,\"name\":\"CROWN Token\",\"symbol\":\"CROWN\",\"logoURI\":\"https://shdw-drive.genesysgo.net/AwJ6W2rRaYCGXimceFuLm5td14fhN1VFEfSYg566RxMD/image.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"crown-by-third-time-games\"}},{\"address\":\"6VNKqgz9hk7zRShTFdg5AnkfKwZUcojzwAkzxSH3bnUm\",\"chainId\":101,\"decimals\":9,\"name\":\"Wrapped HAPI\",\"symbol\":\"wHAPI\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/6VNKqgz9hk7zRShTFdg5AnkfKwZUcojzwAkzxSH3bnUm/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"hapi\"}},{\"address\":\"XBMuuVZKHd6u8GyX6JakhjgpCA6h7FG28bXaWX2s51P\",\"chainId\":101,\"decimals\":9,\"name\":\"Beast Meme\",\"symbol\":\"XBM\",\"logoURI\":\"https://arweave.net/WpHohYQqfwWlp14kMnOjwlcpEhoEibuUsW-MvtDz29A?ext=png\",\"tags\":[\"community\"]},{\"address\":\"ELSnGFd5XnSdYFFSgYQp7n89FEbDqxN4npuRLW4PPPLv\",\"chainId\":101,\"decimals\":8,\"name\":\"HEX (Wormhole v1)\",\"symbol\":\"wHEX_v1\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/ELSnGFd5XnSdYFFSgYQp7n89FEbDqxN4npuRLW4PPPLv/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"]},{\"address\":\"ATLASXmbPQxBUYbxPsV97usA3fPQYEqzQBUHgiFCUsXx\",\"chainId\":101,\"decimals\":8,\"name\":\"Star Atlas\",\"symbol\":\"ATLAS\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/ATLASXmbPQxBUYbxPsV97usA3fPQYEqzQBUHgiFCUsXx/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"star-atlas\"}},{\"address\":\"3zE4Qmh9hrC4inHynT3aSRzHcWhnxPCHLeN9WWqvoRVz\",\"chainId\":101,\"decimals\":9,\"name\":\"Race Raiders Rewards\",\"symbol\":\"RRR\",\"logoURI\":\"https://arweave.net/SH106hrChudKjQ_c6e6yd0tsGUbFIScv2LL6Dp-LDiI\",\"tags\":[\"community\"]},{\"address\":\"DeaKMzAeZja3Mh5okZE6WUvygLP3Lfuvm6Rg78HqXTz9\",\"chainId\":101,\"decimals\":6,\"name\":\"Solnic\",\"symbol\":\"SOLNIC\",\"logoURI\":\"https://gateway.irys.xyz/y0Hlgi1BbUfWCfJ2a407UrlViwN0Fa3cjLikgrVCpWI\",\"tags\":[\"community\"]},{\"address\":\"9QgXH6RjuLx5izvgRU1ovzackRsbzQoe415mxHUZJkkH\",\"chainId\":101,\"decimals\":6,\"name\":\"Keif The Kat\",\"symbol\":\"$KEIF\",\"logoURI\":\"https://cf-ipfs.com/ipfs/QmetHJ1RirB7vth3yznTvUcfqfuzgyWXAFKEFSd4B9P7uu\",\"tags\":[\"community\"]},{\"address\":\"8D1nUMJQam54o34Kj2knFhSTaWoehEr4mBc7LfiDdCqq\",\"chainId\":101,\"decimals\":8,\"name\":\"Sharbi\",\"symbol\":\"SHARBI\",\"logoURI\":\"https://bafkreigwjy43gzwjqlfpottif2pmn3i5fyk7z6p5txvtoceljr4iqxvozq.ipfs.nftstorage.link\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"sharbi\"}},{\"address\":\"FDKBUXKxCdNQnDrqP7DLe8Kri3hzFRxcXyoskoPa74rk\",\"chainId\":101,\"decimals\":9,\"name\":\"YETI\",\"symbol\":\"$YETI\",\"logoURI\":\"https://raw.githubusercontent.com/yet777/tokenlogo/main/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"]},{\"address\":\"Pz1b7iALFqzsgdL9ca6P3NZvTXwSF1koaQqnNohVFcT\",\"chainId\":101,\"decimals\":6,\"name\":\"Garf\",\"symbol\":\"GARF\",\"logoURI\":\"https://pbs.twimg.com/profile_images/1770950513913139200/7WTpGIxH_400x400.jpg\",\"tags\":[\"community\"]},{\"address\":\"9EaLkQrbjmbbuZG9Wdpo8qfNUEjHATJFSycEmw6f1rGX\",\"chainId\":101,\"decimals\":9,\"name\":\"pSOL (Parrot SOL)\",\"symbol\":\"pSOL\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/9EaLkQrbjmbbuZG9Wdpo8qfNUEjHATJFSycEmw6f1rGX/logo.svg\",\"tags\":[\"old-registry\"]},{\"address\":\"AfPeB1BDUotBeNoLv82XRDCNQcdAA1mqis3YC5SMTe7a\",\"chainId\":101,\"decimals\":6,\"name\":\"Wild Goat Coin\",\"symbol\":\"WGC\",\"logoURI\":\"https://gateway.irys.xyz/edk9MSdbuGJv8kXWV7obsLWrIKVPvFpKdwujf4Ss54A\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"wild-goat-coin\"}},{\"address\":\"CobcsUrt3p91FwvULYKorQejgsm5HoQdv5T8RUZ6PnLA\",\"chainId\":101,\"decimals\":8,\"name\":\"ConstitutionDAO (Portal)\",\"symbol\":\"PEOPLE\",\"logoURI\":\"https://raw.githubusercontent.com/wormhole-foundation/wormhole-token-list/main/assets/PEOPLE_wh.png\",\"tags\":[\"wormhole\",\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"constitutiondao-wormhole\"}},{\"address\":\"9cMWa1wuWcio3vgEpiFg7PqKbcoafuUw5sLYFkXJ2J8M\",\"chainId\":101,\"decimals\":8,\"name\":\"Cloned Arbitrum\",\"symbol\":\"clARB\",\"logoURI\":\"https://markets.clone.so/images/assets/on-arb.svg\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"clone-protocol-clarb\"}},{\"address\":\"KgV1GvrHQmRBY8sHQQeUKwTm2r2h8t4C8qt12Cw1HVE\",\"chainId\":101,\"decimals\":8,\"name\":\"AVAX (Portal)\",\"symbol\":\"AVAX\",\"logoURI\":\"https://raw.githubusercontent.com/wormhole-foundation/wormhole-token-list/main/assets/AVAX_wh.png\",\"tags\":[\"wormhole\",\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"avalanche-wormhole\"}},{\"address\":\"3dgCCb15HMQSA4Pn3Tfii5vRk7aRqTH95LJjxzsG2Mug\",\"chainId\":101,\"decimals\":9,\"name\":\"Honeyland\",\"symbol\":\"HXD\",\"logoURI\":\"https://arweave.net/NHZyHFvooDi_LKgs1BaHVPMsuRSOoHpE-J26fp1uHGQ\",\"tags\":[\"community\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"honeyland-honey\"}},{\"address\":\"J1YnyKzmxBwFkPftvZexnHTm4Am7JnmWydhxtXdwEmMv\",\"chainId\":101,\"decimals\":8,\"name\":\"Open Ticketing Ecosystem\",\"symbol\":\"OPN\",\"logoURI\":\"https://arweave.net/4t-p2fZBHNMv7_9mwfFgN1ZWzbHGAUTdIJr399nRCgQ\",\"tags\":[\"community\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"open-ticketing-ecosystem\"}},{\"address\":\"BykkD9369RvXuEDbR7pTRz49b7cfLRTzHgSVoqK8gc15\",\"chainId\":101,\"decimals\":6,\"name\":\"White Coffee Cat\",\"symbol\":\"WCC\",\"logoURI\":\"https://bafybeigm6bjva5igzsz56xb24gbiq3ynu5l2tuxnkbi437hplbw3fu5vxq.ipfs.cf-ipfs.com\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"white-coffee-cat\"}},{\"address\":\"GfJ3Vq2eSTYf1hJP6kKLE9RT6u7jF9gNszJhZwo5VPZp\",\"chainId\":101,\"decimals\":9,\"name\":\"Solpad Finance\",\"symbol\":\"SOLPAD\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/GfJ3Vq2eSTYf1hJP6kKLE9RT6u7jF9gNszJhZwo5VPZp/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"solpad-finance\"}},{\"address\":\"2cpCdyVCpxzwYWFhFqgMJqxnMgEMDGohHUQuf4ppWuAH\",\"chainId\":101,\"decimals\":6,\"name\":\"Thug Frog\",\"symbol\":\"TROG\",\"logoURI\":\"https://bafkreifdk5zl36v7fe5hn2ccbdbt3ot4x25l6l25hjqmb6lzfbpi23yh4q.ipfs.nftstorage.link\",\"tags\":[\"community\"]},{\"address\":\"2kARUpcjqKJdSTgZQKrzABLKbjvi2hNADYwHq7z52gGq\",\"chainId\":101,\"decimals\":9,\"name\":\"Andwu Tet\",\"symbol\":\"TopG\",\"logoURI\":\"https://bafkreifktm2esm67rvurcqhnofh54ipnnqti2mzy3b2yfg65b2577yhrxi.ipfs.nftstorage.link\",\"tags\":[\"community\"]},{\"address\":\"E4Q5pLaEiejwEQHcM9GeYSQfMyGy8DJ4bPWgeYthn24v\",\"chainId\":101,\"decimals\":9,\"name\":\"Ada\",\"symbol\":\"ADA\",\"logoURI\":\"https://gateway.irys.xyz/BFejxwOQrFxDnvG84t8kUf3PI_nCU3PEBrHBlu6g5ww\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"ada-the-dog\"}},{\"address\":\"BABYsocP6cB95xvBDXnjXKX96VBNC37dmNWUtaV9Jk6v\",\"chainId\":101,\"decimals\":2,\"name\":\"SOL BABAY DOGE COIN\",\"symbol\":\"SBABYDOGE\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/BABYsocP6cB95xvBDXnjXKX96VBNC37dmNWUtaV9Jk6v/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"sol-baby-doge\"}},{\"address\":\"DEVwHJ57QMPPArD2CyjboMbdWvjEMjXRigYpaUNDTD7o\",\"chainId\":101,\"decimals\":6,\"name\":\"DevWifHat\",\"symbol\":\"DWH\",\"logoURI\":\"https://arweave.net/8axP5eu3H87THPNK3oWV0dJ_-z1zoZGhp-V8LgJ3IBU\",\"tags\":[\"community\"]},{\"address\":\"CvB1ztJvpYQPvdPBePtRzjL4aQidjydtUz61NWgcgQtP\",\"chainId\":101,\"decimals\":6,\"name\":\"Epics Token\",\"symbol\":\"EPCT\",\"logoURI\":\"https://bafybeibal7k2hz6frznyjbl4qcnzcwlsuzxmquatrsaly6ttmuppgmdvwe.ipfs.dweb.link/EpicsCoin.png\",\"tags\":[\"community\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"epics-token\"}},{\"address\":\"9w6LpS7RU1DKftiwH3NgShtXbkMM1ke9iNU4g3MBXSUs\",\"chainId\":101,\"decimals\":9,\"name\":\"Wrapped DAI (Allbridge from Ethereum)\",\"symbol\":\"aeDAI\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/FYpdBuyAHSbdaAyD1sKkxyLWbAP8uUW9h6uvdhK74ij1/logo.png\",\"tags\":[\"old-registry\"]},{\"address\":\"AYyYgh3i43s1QSpvG4vwhJ6s3gewfN7uteFwYrswgMGw\",\"chainId\":101,\"decimals\":9,\"name\":\"ps1 hagrid\",\"symbol\":\"HAGGORD\",\"logoURI\":\"https://bafkreiak2a2gbdqqnz77xup3bi72lon5fe4ri4cpozxp5o4ekjnugm77wm.ipfs.nftstorage.link\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"haggord\"}},{\"address\":\"GmW12mAzyTj897Y3pgxDQzpnNid7q58E8T7V56rmaUdD\",\"chainId\":101,\"decimals\":6,\"name\":\"Community of Meme\",\"symbol\":\"COME\",\"logoURI\":\"https://photos.pinksale.finance/file/pinksale-logo-upload/1710895928245-3b82d54c6607c34a0499ee540316e572.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"community-of-meme\"}},{\"address\":\"E5ndSkaB17Dm7CsD22dvcjfrYSDLCxFcMd6z8ddCk5wp\",\"chainId\":101,\"decimals\":9,\"name\":\"Aldrin\",\"symbol\":\"RIN\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/E5ndSkaB17Dm7CsD22dvcjfrYSDLCxFcMd6z8ddCk5wp/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"aldrin\"}},{\"address\":\"5LSFpvLDkcdV2a3Kiyzmg5YmJsj2XDLySaXvnfP1cgLT\",\"chainId\":101,\"decimals\":6,\"name\":\"Dogemon\",\"symbol\":\"DOGO\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/5LSFpvLDkcdV2a3Kiyzmg5YmJsj2XDLySaXvnfP1cgLT/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"dogemon-go\"}},{\"address\":\"Cj2NAxiDDVvY79PLBdVQK3F3bjoHp7hvZv4kLL1vgtV3\",\"chainId\":101,\"decimals\":2,\"name\":\"Mad Lads\",\"symbol\":\"MADx\",\"logoURI\":\"https://creator-hub-prod.s3.us-east-2.amazonaws.com/mad_lads_pfp_1682211343777.png\",\"tags\":[\"community\"]},{\"address\":\"kNkT1RDnexWqYP3EYGyWv5ZtazB8CfgGAfJtv9AQ3kz\",\"chainId\":101,\"decimals\":9,\"name\":\"Kineko\",\"symbol\":\"KNK\",\"logoURI\":\"https://www.arweave.net/u7prAs3T9UHwykErAXDfX306yAxFU08PpgvN819K_so?ext=png\",\"tags\":[\"community\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"kineko-knk\"}},{\"address\":\"29ad8JW1YcVT3mxcvxJpe3EzWqXCGD7KaSRWrc3TEMWG\",\"chainId\":101,\"decimals\":6,\"name\":\"Cris Hensan\",\"symbol\":\"SEAT\",\"logoURI\":\"https://cf-ipfs.com/ipfs/QmYNgQq18FFX9ybECYMF7v4JqHRmDwojrJTv58nrFRsrha\",\"tags\":[\"community\"]},{\"address\":\"DtgDZb83TqywcuBuWE89jx4k5Y7b6nQ4GYJq3Wd61JQQ\",\"chainId\":101,\"decimals\":6,\"name\":\"jeo rogen\",\"symbol\":\"rogen\",\"logoURI\":\"https://cf-ipfs.com/ipfs/QmZ5WeZCweWhbKbJ7Ux1ewxVVMrwz5dYL6szeBdT59f4AF\",\"tags\":[\"community\"]},{\"address\":\"AAmGoPDFLG6bE82BgZWjVi8k95tj9Tf3vUN7WvtUm2BU\",\"chainId\":101,\"decimals\":6,\"name\":\"RaceFi Token\",\"symbol\":\"RACEFI\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/AAmGoPDFLG6bE82BgZWjVi8k95tj9Tf3vUN7WvtUm2BU/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"racefi\"}},{\"address\":\"StepAscQoEioFxxWGnh2sLBDFp9d8rvKz2Yp39iDpyT\",\"chainId\":101,\"decimals\":9,\"name\":\"Step\",\"symbol\":\"STEP\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/StepAscQoEioFxxWGnh2sLBDFp9d8rvKz2Yp39iDpyT/logo.png\",\"tags\":[\"old-registry\"],\"extensions\":{\"coingeckoId\":\"step-finance\"}},{\"address\":\"pawSXHWsonrTey4SX7tz1fM9ksuLpE13Y54K57ym4Rg\",\"chainId\":101,\"decimals\":6,\"name\":\"PayPaw\",\"symbol\":\"PAW\",\"logoURI\":\"https://shdw-drive.genesysgo.net/83iEXvkKHm2n1J5wGTopwUHEG7DQbyxJcsH6UmKtVJD/ppaw.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"paypaw\"}},{\"address\":\"DcUoGUeNTLhhzyrcz49LE7z3MEFwca2N9uSw1xbVi1gm\",\"chainId\":101,\"decimals\":9,\"name\":\"K-Pop\",\"symbol\":\"KPOP\",\"logoURI\":\"https://arweave.net/rzUo3sj5mtF5Q1ceUp0R7X_BOdULgdbL9OhzaFoP11U\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"k-pop-on-solana\"}},{\"address\":\"DDti34vnkrCehR8fih6dTGpPuc3w8tL4XQ4QLQhc3xPa\",\"chainId\":101,\"decimals\":9,\"name\":\"Liquid Solana Derivative 42069\",\"symbol\":\"LSD\",\"logoURI\":\"https://arweave.net/b4cfT3yHTxVvuZc2jzmgzWUiEBiuAC9hkM7GhXZEklg\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"liquid-solana-derivative\"}},{\"address\":\"BhPXDQio8xtNC6k5Bg5fnUVL9kGN8uvRDNwW8MZBu8DL\",\"chainId\":101,\"decimals\":4,\"name\":\"Shibana\",\"symbol\":\"BANA\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/BhPXDQio8xtNC6k5Bg5fnUVL9kGN8uvRDNwW8MZBu8DL/Shibana.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"shibana\"}},{\"address\":\"96QoNkZLTKH7Gecf6dKMRXBQXW1wqh378uF9pnTejgAw\",\"chainId\":101,\"decimals\":9,\"name\":\"Popo Pepe's Dog\",\"symbol\":\"$POPO\",\"logoURI\":\"https://gateway.irys.xyz/NsqnWfvg7r-SmICp9Gy5fIEBFWYlshxcSiV92H39_k4\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"popo-pepe-s-dog\"}},{\"address\":\"3Rcc6tMyS7ZEa29dxV4g3J5StorS9J1dn98gd42pZTk1\",\"chainId\":101,\"decimals\":6,\"name\":\"MIMANY\",\"symbol\":\"MIMANY\",\"logoURI\":\"https://bafybeicmv6htlk6vqunx4jifj52txzebkmvxiq3ph66igoqwbqnzkcbntq.ipfs.cf-ipfs.com/\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"mimany\"}},{\"address\":\"HmKqChBkZEvqFnH8sxja694n77ziYMBWaucZRKfJDRr2\",\"chainId\":101,\"decimals\":5,\"name\":\"WOLF SOLANA\",\"symbol\":\"WOLF\",\"logoURI\":\"https://arweave.net/vqdJ1M-3GLpOKuMKZLXNbd-odMLzGrXdgVis8wSEt-M\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"wolf-solana\"}},{\"address\":\"9gwTegFJJErDpWJKjPfLr2g2zrE3nL1v5zpwbtsk3c6P\",\"chainId\":101,\"decimals\":9,\"name\":\"A Gently Used 2001 Honda Civic\",\"symbol\":\"USEDCAR\",\"logoURI\":\"https://gateway.irys.xyz/7TTPG48afTZE8Ioeaa4XlZ7F4Q5g4lLYkC7N1Yr1pFo\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"a-gently-used-2001-honda\"}},{\"address\":\"kinXdEcpDQeHPEuQnqmUgtYykqKGVFq6CeVX5iAHJq6\",\"chainId\":101,\"decimals\":5,\"name\":\"KIN\",\"symbol\":\"KIN\",\"logoURI\":\"https://i.imgur.com/do6LTig.jpeg\",\"tags\":[\"community\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"kin\"}},{\"address\":\"5Wsd311hY8NXQhkt9cWHwTnqafk7BGEbLu8Py3DSnPAr\",\"chainId\":101,\"decimals\":6,\"name\":\"Compendium Finance\",\"symbol\":\"CMFI\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/5Wsd311hY8NXQhkt9cWHwTnqafk7BGEbLu8Py3DSnPAr/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"compendium-fi\"}}]";
//
//        List<JupToken> datas=parseTokenData(jub_str);

        //初始化热门代币-从中排除已经添加过的代币
        return try {
            val hotdatasbytes = MMKV.mmkvWithID(GlobalConstant.GROUP_WALLET_DATAS)
                .decodeBytes(GlobalConstant.GROUP_WALLET_DATAS_STRICT, null)
            val hotdatas =
                ObjectSerializationUtils.deserializeObject(hotdatasbytes) as List<UiWalletToken>
            val hottokensCopy: List<UiWalletToken> = ArrayList(hotdatas)
            val isRemoveAll = TokenConverter.FilterJubTokens(hottokensCopy, localdatas)
            if (isRemoveAll) {
                viewModel!!.hotdatas.postValue(hottokensCopy)
            }
            hottokensCopy
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun initSearchData(localdatas: List<UiWalletToken>?, hotdatas: List<UiWalletToken>?) {
        if (localdatas == null || hotdatas == null) return

        //初始化总的数据源
        val amountDatas: MutableList<UiWalletToken> = ArrayList()
        amountDatas.addAll(localdatas)
        amountDatas.addAll(hotdatas)
        viewModel!!.amountdatas.postValue(amountDatas)
        //用于备份原始数据 在amountdatascache里面查数据 ，把结果post到amountdatas这里
        viewModel!!.amountdatascache.postValue(amountDatas)
        //关闭 清理图标
        viewModel!!.clearAll.set(false)
        //还原edittext显示内容
        viewModel!!.empty.set("")
    }

    private val onEditFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
        // 当焦点获取时，启动光标闪烁动画
        if (hasFocus) {
            //显示搜索布局
            viewModel!!.showSearchLayout.set(true)
        } else {
            //隐藏搜索布局
            viewModel!!.showSearchLayout.set(false)
            initData(keyAlias)
        }
    }
    private val onSearchTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            //获取当前的amountdatascache 数据
            val cacheTokens = viewModel!!.amountdatascache.value!!
            if (charSequence == null) return
            if (charSequence.toString().trim { it <= ' ' }.length == 0) {
                viewModel!!.amountdatas.postValue(cacheTokens)
                viewModel!!.clearAll.set(false)
            } else {
                if (viewModel!!.showSearchLayout.get()!!) {
                    viewModel!!.clearAll.set(true)
                } else {
                    viewModel!!.clearAll.set(false)
                }
            }


            //在这里查询
            val searchResult = SearchTokenUtils.searchTokens(
                cacheTokens,
                charSequence.toString().trim { it <= ' ' })
            viewModel!!.amountdatas.postValue(searchResult)
        }

        override fun afterTextChanged(editable: Editable) {}
    }

    inner class ClickProxy {
        fun close() {
            finish()
        }

        fun clearAllText() {
            viewModel!!.empty.set("")
        }

        fun cancelSearchModel() {

            //关闭搜索模式总开关
            viewModel!!.showSearchLayout.set(false)

//            initData();
        }

        fun startCusCoinAdd() {
            IntentUtils.openIntent(
                this@CoinManagerActivity,
                Intent(
                    this@CoinManagerActivity,
                    CusAddCoinActivity::class.java
                ).putExtra("keyAlias", keyAlias)
            )
            finish()
        }
    }

    companion object {
        fun parseTokenData(jsonString: String?): List<JupToken> {
            val gson = Gson()
            val listType = object : TypeToken<List<JupToken?>?>() {}.type
            return gson.fromJson(jsonString, listType)
        }
    }
}