package com.revature.JJLZ.service;

import com.revature.JJLZ.model.StockWatchlist;
import com.revature.JJLZ.model.User;
import com.revature.JJLZ.repository.WatchlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;

public class WatchListServiceTest extends Mockito {
    WatchlistService watchlistService;
    @Mock
    WatchlistRepository mockWatchlistRepository;
    @Mock
    User mockUser;
    @Mock
    StockWatchlist stockWatchlist;

    @BeforeEach
    public void setup(){
        watchlistService = new WatchlistService(mockWatchlistRepository);

    }
}
